/**
 * 
 */
package br.com.cams7.sisbarc.aal.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.com.cams7.sisbarc.aal.jpa.domain.Pin;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin.Evento;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin.Intervalo;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.jpa.domain.pk.PinPK;
import br.com.cams7.sisbarc.arduino.ArduinoService;

/**
 * @author cesar
 *
 */
@WebService
@SOAPBinding(style = Style.RPC)
// @SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use =
// SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface AppArduinoService extends ArduinoService {

	/**
	 * Altera o ESTADO do LED para ACESO ou APAGADO
	 * 
	 * @param PINO
	 *            do LED - Numero do PINO DIGITAL
	 * @param ESTADO
	 *            do LED - ACESO/APAGADO
	 * 
	 * 
	 */
	@WebMethod
	public EstadoLED alteraEstadoLED(PinPK pinoId, EstadoLED estado);

	/**
	 * Busca os ESTADOs dos LEDs, que pode ser ACESO ou APAGADO
	 * 
	 * @param PINOs
	 *            dos LEDs - Numero do PINO DIGITAL
	 */
	@WebMethod
	public LEDEntity[] buscaEstadoLEDs(PinPK[] ids);

	/**
	 * Altera o EVENTO e o INTERVALO
	 * 
	 * @param PINO
	 *            - Numero do PINO DIGITAL/ANALOGICO
	 * @param EVENTO
	 * 
	 * @param INTERVALO
	 */
	@WebMethod
	public Evento alteraEvento(PinPK pinoId, Evento evento, Intervalo intervalo);

	/**
	 * @param pinos
	 * @return
	 */
	@WebMethod
	public Pin[] alteraEventos(Pin[] pinos);

	/**
	 * Obtem os Dados na EEPROM do ARDUINO
	 * 
	 * @param PINOs
	 *            - Numero do PINO DIGITAL/ANALOGICO
	 */
	@WebMethod
	public Pin[] buscaDados(PinPK[] ids);

}
