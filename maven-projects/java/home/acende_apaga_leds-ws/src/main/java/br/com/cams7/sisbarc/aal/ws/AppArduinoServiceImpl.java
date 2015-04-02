/**
 * 
 */
package br.com.cams7.sisbarc.aal.ws;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import br.com.cams7.sisbarc.aal.AppContextListener;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin.Evento;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin.Intervalo;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.jpa.domain.pk.PinPK;
import br.com.cams7.sisbarc.arduino.ArduinoException;

/**
 * @author cams7
 *
 */
@WebService(endpointInterface = "br.com.cams7.sisbarc.aal.ws.AppArduinoService")
public class AppArduinoServiceImpl implements AppArduinoService {

	@Resource
	private WebServiceContext context;

	private AppArduinoService getMonitor() {
		ServletContext servletContext = (ServletContext) context
				.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		AppArduinoService monitor = (AppArduinoService) servletContext
				.getAttribute(AppContextListener.MONITOR);
		return monitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.arduino.ArduinoService#getSerialPort()
	 */
	@Override
	public String getSerialPort() {
		AppArduinoService monitor = getMonitor();
		return monitor.getSerialPort();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.arduino.ArduinoService#getSerialBaudRate()
	 */
	@Override
	public int getSerialBaudRate() {
		AppArduinoService monitor = getMonitor();
		return monitor.getSerialBaudRate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.arduino.ArduinoService#getSerialThreadTime()
	 */
	@Override
	public long getSerialThreadTime() {
		AppArduinoService monitor = getMonitor();
		return monitor.getSerialThreadTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.arduino.ArduinoService#init()
	 */
	@Override
	public void init() throws ArduinoException {
		AppArduinoService monitor = getMonitor();
		monitor.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.arduino.ArduinoService#close()
	 */
	@Override
	public void close() throws ArduinoException {
		AppArduinoService monitor = getMonitor();
		monitor.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.arduino.ArduinoService#isInitialized()
	 */
	@Override
	public boolean isInitialized() {
		AppArduinoService monitor = getMonitor();
		return monitor.isInitialized();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.ws.AppArduinoService#alteraEstadoLED(br.com.
	 * cams7.sisbarc.aal.jpa.domain.pk.PinPK,
	 * br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity.EstadoLED)
	 */
	@Override
	public EstadoLED alteraEstadoLED(PinPK pinoId, EstadoLED estado) {
		AppArduinoService monitor = getMonitor();
		return monitor.alteraEstadoLED(pinoId, estado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.ws.AppArduinoService#buscaEstadoLED(br.com.cams7
	 * .sisbarc.aal.jpa.domain.pk.PinPK)
	 */
	@Override
	public LEDEntity[] buscaEstadoLEDs(PinPK[] ids) {
		AppArduinoService monitor = getMonitor();
		return monitor.buscaEstadoLEDs(ids);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.ws.AppArduinoService#alteraEvento(br.com.cams7
	 * .sisbarc.aal.jpa.domain.pk.PinPK,
	 * br.com.cams7.sisbarc.aal.jpa.domain.Pin.Evento,
	 * br.com.cams7.sisbarc.aal.jpa.domain.Pin.Intervalo)
	 */
	@Override
	public Evento alteraEvento(PinPK pinoId, Evento evento, Intervalo intervalo) {
		AppArduinoService monitor = getMonitor();
		return monitor.alteraEvento(pinoId, evento, intervalo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.ws.AppArduinoService#alteraEventos(java.util
	 * .List)
	 */
	@Override
	public Pin[] alteraEventos(Pin[] pinos) {
		AppArduinoService monitor = getMonitor();
		return monitor.alteraEventos(pinos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.ws.AppArduinoService#buscaDados(br.com.cams7
	 * .sisbarc.aal.jpa.domain.pk.PinPK)
	 */
	@Override
	public Pin[] buscaDados(PinPK[] ids) {
		AppArduinoService monitor = getMonitor();
		return monitor.buscaDados(ids);
	}

}
