package br.com.cams7.sisbarc.aal.controler.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import br.com.cams7.sisbarc.aal.ws.LEDService;

@RestController
@RequestMapping("/rest")
public class LEDRestCtrl {

	private final Logger LOG = Logger.getLogger(LEDRestCtrl.class.getName());

	@Autowired
	private ServletContext context;

	private LEDService getMonitor() {
		LEDService monitor = (LEDService) context
				.getAttribute(AppContextListener.MONITOR);
		return monitor;
	}

	// LED Amarela - /rest/led/DIGITAL/11/ACESO
	// LED Verde - /rest/led/DIGITAL/10/APAGADO
	// LED Vermelha - /rest/led/DIGITAL/9/APAGADO
	@RequestMapping(value = "/led/{tipo_pino}/{pino}/{estado}", method = RequestMethod.GET)
	public @ResponseBody LEDEntity alteraLEDEstado(
			@PathVariable("tipo_pino") String stringTipoPino,
			@PathVariable("pino") String stringPino,
			@PathVariable("estado") String stringEstado)
			throws ArduinoException {

		ArduinoPinType tipoPino = ArduinoPinType.valueOf(stringTipoPino);
		Short pino = Short.valueOf(stringPino);

		LEDEntity led = new LEDEntity(new PinPK(tipoPino, pino));
		led.setEstado(EstadoLED.valueOf(stringEstado));

		EstadoLED estado = getMonitor().alteraEstadoLED(led.getId(),
				led.getEstado());

		led.setEstado(estado);

		if (estado != null) {
			LOG.info("LED '" + led.getId() + "' esta '" + led.getEstado() + "'");
		} else
			LOG.log(Level.WARNING,
					"Ocorreu um erro ao tenta buscar o ESTADO do LED '"
							+ led.getId() + "'");

		return led;
	}

	@ExceptionHandler({ ArduinoException.class })
	public @ResponseBody ResponseEntity<?> handleArduinoException(
			ArduinoException exception) {
		String errorMessage = exception.getMessage();

		ResponseEntity<?> response = new ResponseEntity<>(
				getHeaders(errorMessage), HttpStatus.NOT_FOUND);
		LOG.log(Level.WARNING, errorMessage);

		return response;
	}

	private HttpHeaders getHeaders(String message) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("errorMessage", message);
		return headers;
	}

}
