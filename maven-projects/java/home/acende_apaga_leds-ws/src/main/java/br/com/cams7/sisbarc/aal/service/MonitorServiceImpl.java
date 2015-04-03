/**
 * 
 */
package br.com.cams7.sisbarc.aal.service;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.sisbarc.aal.AppContextListener;
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
@WebService(endpointInterface = "br.com.cams7.sisbarc.aal.service.MonitorService")
public class MonitorServiceImpl implements MonitorService {

	@Resource
	private WebServiceContext context;

	private MonitorService getMonitor() {
		ServletContext servletContext = (ServletContext) context
				.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		MonitorService monitor = (MonitorService) servletContext
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
		return getMonitor().getSerialPort();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.arduino.ArduinoService#getSerialBaudRate()
	 */
	@Override
	public int getSerialBaudRate() {
		return getMonitor().getSerialBaudRate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.arduino.ArduinoService#getSerialThreadTime()
	 */
	@Override
	public long getSerialThreadTime() {
		return getMonitor().getSerialThreadTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.arduino.ArduinoService#init()
	 */
	@Override
	public void init() throws ArduinoException {
		getMonitor().init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.arduino.ArduinoService#close()
	 */
	@Override
	public void close() throws ArduinoException {
		getMonitor().close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.arduino.ArduinoService#isInitialized()
	 */
	@Override
	public boolean isInitialized() {
		return getMonitor().isInitialized();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.aal.ws.MonitorService#alteraEstadoLED(br.com.
	 * cams7.sisbarc.aal.jpa.domain.pk.PinPK,
	 * br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity.EstadoLED)
	 */
	@Override
	public EstadoLED alteraEstadoLED(PinPK pinoId, EstadoLED estado) {
		return getMonitor().alteraEstadoLED(pinoId, estado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.ws.MonitorService#buscaEstadoLED(br.com.cams7
	 * .sisbarc.aal.jpa.domain.pk.PinPK)
	 */
	@Override
	public LEDEntity[] buscaEstadoLEDs(PinPK[] ids) {
		return getMonitor().buscaEstadoLEDs(ids);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.aal.ws.MonitorService#alteraEvento(br.com.cams7
	 * .sisbarc.aal.jpa.domain.pk.PinPK,
	 * br.com.cams7.sisbarc.aal.jpa.domain.Pin.Evento,
	 * br.com.cams7.sisbarc.aal.jpa.domain.Pin.Intervalo)
	 */
	@Override
	public Evento alteraEvento(PinPK pinoId, Evento evento, Intervalo intervalo) {
		return getMonitor().alteraEvento(pinoId, evento, intervalo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.aal.ws.MonitorService#alteraEventos(java.util
	 * .List)
	 */
	@Override
	public Pin[] alteraEventos(Pin[] pinos) {
		return getMonitor().alteraEventos(pinos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.sisbarc.aal.ws.MonitorService#buscaDados(br.com.cams7
	 * .sisbarc.aal.jpa.domain.pk.PinPK)
	 */
	@Override
	public Pin[] buscaDados(PinPK[] ids) {
		return getMonitor().buscaDados(ids);
	}

}
