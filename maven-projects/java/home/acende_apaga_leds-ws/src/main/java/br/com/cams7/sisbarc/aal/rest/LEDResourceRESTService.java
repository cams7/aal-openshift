package br.com.cams7.sisbarc.aal.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.sisbarc.aal.AppContextListener;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.jpa.domain.pk.PinPK;
import br.com.cams7.sisbarc.aal.service.MonitorService;
import br.com.cams7.util.AppUtil;

@Path("/")
public class LEDResourceRESTService {

	private final Logger LOG = Logger.getLogger(LEDResourceRESTService.class
			.getName());

	@Context
	private ServletContext context;

	private MonitorService getMonitor() {
		MonitorService monitor = (MonitorService) context
				.getAttribute(AppContextListener.MONITOR);
		return monitor;
	}

	// LED Amarela - arduino/led?tipo_pino=DIGITAL&pino=11&estado=ON
	// LED Verde - arduino/led?tipo_pino=DIGITAL&pino=10&estado=OFF
	// LED Vermelha - arduino/led?tipo_pino=DIGITAL&pino=9&estado=OFF
	@GET
	@Path("/led")
	@Produces(MediaType.APPLICATION_JSON)
	public Response alteraLEDEstado(
			@QueryParam("tipo_pino") String stringTipoPino,
			@QueryParam("pino") String stringPino,
			@QueryParam("estado") String stringEstado) {

		ArduinoPinType tipoPino = ArduinoPinType.valueOf(stringTipoPino);
		Short pino = Short.valueOf(stringPino);

		LEDEntity led = new LEDEntity(new PinPK(tipoPino, pino));
		led.setEstado(EstadoLED.valueOf(stringEstado));

		Response.ResponseBuilder builder;

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

			builder = Response.status(Response.Status.OK).entity(led);
		} catch (ArduinoException e) {
			builder = Response.status(Response.Status.NOT_FOUND).entity(
					AppUtil.getErrorResponse(e.getMessage()));
		}

		return builder.build();
	}

}
