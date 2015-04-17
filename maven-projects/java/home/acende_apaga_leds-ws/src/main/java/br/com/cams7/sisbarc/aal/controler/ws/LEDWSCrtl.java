/**
 * 
 */
package br.com.cams7.sisbarc.aal.controler.ws;

import javax.jws.WebService;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.sisbarc.aal.AppContextListener;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin.Evento;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin.Intervalo;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.jpa.domain.pk.PinPK;
import br.com.cams7.sisbarc.aal.ws.LEDService;

/**
 * @author cams7
 *
 */
@Controller("ledWS")
@WebService(endpointInterface = LEDService.FULLNAME)
public class LEDWSCrtl implements LEDService {

	@Autowired
	private ServletContext context;

	private LEDService getMonitor() {
		LEDService monitor = (LEDService) context
				.getAttribute(AppContextListener.MONITOR);
		return monitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.arduino.ArduinoService#getSerialPort()
	 */
	@Override
	public String getSerialPort() {
		return getMonitor().getSerialPort();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.arduino.ArduinoService#getSerialBaudRate()
	 */
	@Override
	public int getSerialBaudRate() {
		return getMonitor().getSerialBaudRate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.arduino.ArduinoService#getSerialThreadTime()
	 */
	@Override
	public long getSerialThreadTime() {
		return getMonitor().getSerialThreadTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.arduino.ArduinoService#init()
	 */
	@Override
	public void init() throws ArduinoException {
		getMonitor().init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.arduino.ArduinoService#close()
	 */
	@Override
	public void close() throws ArduinoException {
		getMonitor().close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.arduino.ArduinoService#isInitialized()
	 */
	@Override
	public boolean isInitialized() {
		return getMonitor().isInitialized();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.service.MonitorService#alteraEstadoLED(br.com
	 * .cams7.sisbarc.aal.jpa.domain.pk.PinPK,
	 * br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity.EstadoLED)
	 */
	@Override
	public EstadoLED alteraEstadoLED(PinPK pinoId, EstadoLED estado)
			throws ArduinoException {
		return getMonitor().alteraEstadoLED(pinoId, estado);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.service.MonitorService#buscaEstadoLEDs(br.com
	 * .cams7.sisbarc.aal.jpa.domain.pk.PinPK[])
	 */
	@Override
	public LEDEntity[] buscaEstadoLEDs(PinPK[] ids) throws ArduinoException {
		return getMonitor().buscaEstadoLEDs(ids);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.service.MonitorService#alteraEvento(br.com.cams7
	 * .sisbarc.aal.jpa.domain.pk.PinPK,
	 * br.com.cams7.sisbarc.aal.jpa.domain.Pin.Evento,
	 * br.com.cams7.sisbarc.aal.jpa.domain.Pin.Intervalo)
	 */
	@Override
	public Evento alteraEvento(PinPK pinoId, Evento evento, Intervalo intervalo)
			throws ArduinoException {
		return getMonitor().alteraEvento(pinoId, evento, intervalo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.service.MonitorService#alteraEventos(br.com.
	 * cams7.sisbarc.aal.jpa.domain.Pin[])
	 */
	@Override
	public Pin[] alteraEventos(Pin[] pinos) throws ArduinoException {
		return getMonitor().alteraEventos(pinos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.service.MonitorService#buscaDados(br.com.cams7
	 * .sisbarc.aal.jpa.domain.pk.PinPK[])
	 */
	@Override
	public Pin[] buscaDados(PinPK[] ids) throws ArduinoException {
		return getMonitor().buscaDados(ids);
	}

}
