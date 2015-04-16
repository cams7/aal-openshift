package br.com.cams7.sisbarc.aal.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.sisbarc.aal.AppContextListener;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.jpa.domain.pk.PinPK;
import br.com.cams7.sisbarc.aal.service.MonitorService;

@RestController
@RequestMapping("/rest")
public class LEDResourceRESTService {

	private final Logger LOG = Logger.getLogger(LEDResourceRESTService.class
			.getName());

	@Autowired
	private ServletContext context;

	private MonitorService getMonitor() {
		MonitorService monitor = (MonitorService) context
				.getAttribute(AppContextListener.MONITOR);
		return monitor;
	}

	// LED Amarela - /rest/led/DIGITAL/11/ACESO
	// LED Verde - /rest/led/DIGITAL/10/APAGADO
	// LED Vermelha - /rest/led/DIGITAL/9/APAGADO
	@RequestMapping(value = "/led/{tipo_pino}/{pino}/{estado}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> alteraLEDEstado(
			@PathVariable("tipo_pino") String stringTipoPino,
			@PathVariable("pino") String stringPino,
			@PathVariable("estado") String stringEstado) {

		ArduinoPinType tipoPino = ArduinoPinType.valueOf(stringTipoPino);
		Short pino = Short.valueOf(stringPino);

		LEDEntity led = new LEDEntity(new PinPK(tipoPino, pino));
		led.setEstado(EstadoLED.valueOf(stringEstado));

		ResponseEntity<?> response;

		try {
			EstadoLED estado = getMonitor().alteraEstadoLED(led.getId(),
					led.getEstado());

			led.setEstado(estado);

			if (estado != null) {
				LOG.info("LED '" + led.getId() + "' esta '" + led.getEstado()
						+ "'");
			} else
				LOG.log(Level.WARNING,
						"Ocorreu um erro ao tenta buscar o ESTADO do LED '"
								+ led.getId() + "'");

			response = new ResponseEntity<>(led, HttpStatus.OK);
		} catch (ArduinoException e) {
			response = new ResponseEntity<>(getHeaders(e.getMessage()),
					HttpStatus.NOT_FOUND);
			LOG.log(Level.SEVERE, e.getMessage());
		}

		return response;
	}

	private HttpHeaders getHeaders(String message) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("errorMessage", message);
		return headers;
	}

}
