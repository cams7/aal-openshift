/**
 * 
 */
package br.com.cams7.sisbarc.aal.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.arduino.ArduinoService;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin.Evento;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin.Intervalo;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.jpa.domain.pk.PinPK;

/**
 * @author cams7
 *
 */
@WebService(name = MonitorService.WEBSERVICE_NAME, targetNamespace = MonitorService.WEBSERVICE_TARGETNAMESPACE)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MonitorService extends ArduinoService {

	public static final String WEBSERVICE_NAME = "MonitorService";
	public static final String WEBSERVICE_TARGETNAMESPACE = "http://service.aal.sisbarc.cams7.com.br/";

	public static final String WEBSERVICE_FULLNAME = WEBSERVICE_TARGETNAMESPACE
			+ WEBSERVICE_NAME;

	/**
	 * Altera o ESTADO do LED para ACESO ou APAGADO
	 * 
	 * @param PINO
	 *            do LED - Numero do PINO DIGITAL
	 * @param ESTADO
	 *            do LED - ACESO/APAGADO
	 * @return
	 * @throws ArduinoException
	 */
	@WebMethod
	public EstadoLED alteraEstadoLED(PinPK pinoId, EstadoLED estado)
			throws ArduinoException;

	/**
	 * Busca os ESTADOs dos LEDs, que pode ser ACESO ou APAGADO
	 * 
	 * @param PINOs
	 *            dos LEDs - Numero do PINO DIGITAL
	 * @return
	 * @throws ArduinoException
	 */
	@WebMethod
	public LEDEntity[] buscaEstadoLEDs(PinPK[] ids) throws ArduinoException;

	/**
	 * Altera o EVENTO e o INTERVALO
	 * 
	 * @param PINO
	 *            - Numero do PINO DIGITAL/ANALOGICO
	 * @param EVENTO
	 * 
	 * @param INTERVALO
	 * @return
	 * @throws ArduinoException
	 */
	@WebMethod
	public Evento alteraEvento(PinPK pinoId, Evento evento, Intervalo intervalo)
			throws ArduinoException;

	/**
	 * @param pinos
	 * @return
	 * @throws ArduinoException
	 */
	@WebMethod
	public Pin[] alteraEventos(Pin[] pinos) throws ArduinoException;

	/**
	 * Obtem os Dados na EEPROM do ARDUINO
	 * 
	 * @param PINOs
	 *            - Numero do PINO DIGITAL/ANALOGICO
	 * @return
	 * @throws ArduinoException
	 */
	@WebMethod
	public Pin[] buscaDados(PinPK[] ids) throws ArduinoException;

}
