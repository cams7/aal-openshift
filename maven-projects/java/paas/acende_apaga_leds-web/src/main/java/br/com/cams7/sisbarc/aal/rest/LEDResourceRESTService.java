/**
 * 
 */
package br.com.cams7.sisbarc.aal.rest;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.jpa.domain.pk.PinPK;
import br.com.cams7.sisbarc.aal.service.ejb.LEDService;
import br.com.cams7.util.AppException;
import br.com.cams7.util.AppUtil;

import javax.ws.rs.WebApplicationException;

/**
 * @author cams7
 *
 */
@Path("/")
@RequestScoped
public class LEDResourceRESTService {

	@EJB
	private LEDService service;

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

		LEDEntity led = service.findOne(new PinPK(tipoPino, pino));
		led.setEstado(EstadoLED.valueOf(stringEstado));

		Response.ResponseBuilder builder;

		try {
			Future<LEDEntity> call = service.alteraLEDEstado(led);
			led = call.get();
			builder = Response.status(Response.Status.OK).entity(led);
		} catch (InterruptedException | ExecutionException e) {
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(AppUtil.getErrorResponse(AppUtil
							.getExceptionMessage(e.getMessage())));
		} catch (ArduinoException e) {
			builder = Response.status(Response.Status.NOT_FOUND).entity(
					AppUtil.getErrorResponse(e.getMessage()));
		}

		return builder.build();
	}

	@GET
	@Path("/leds")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLEDs() {

		Response.ResponseBuilder builder;
		try {
			Future<List<LEDEntity>> call = service.getLEDsAtivadoPorBotao();
			List<LEDEntity> leds = call.get();

			builder = Response.status(Response.Status.OK).entity(leds);
		} catch (InterruptedException | ExecutionException e) {
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(AppUtil.getErrorResponse(AppUtil
							.getExceptionMessage(e.getMessage())));
		} catch (ArduinoException e) {
			builder = Response.status(Response.Status.NOT_FOUND).entity(
					AppUtil.getErrorResponse(e.getMessage()));
		}

		return builder.build();
	}

	@GET
	@Path("/led/{pino : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEstadoLEDAtivadoPorBotao(@PathParam("pino") byte pin) {
		try {
			EstadoLED estado = service.getEstadoLEDAtivadoPorBotao(pin);
			return Response.status(Response.Status.OK).entity(estado).build();
		} catch (AppException e) {
			throw new WebApplicationException(e.getMessage(),
					Response.Status.NOT_FOUND);
		}
	}

}
