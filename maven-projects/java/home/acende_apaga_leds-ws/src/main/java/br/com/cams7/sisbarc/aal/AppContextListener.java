/**
 * 
 */
package br.com.cams7.sisbarc.aal;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.sisbarc.aal.service.MonitorService;

/**
 * @author cams7
 *
 */
@WebListener
public class AppContextListener implements ServletContextListener {
	private static final Logger LOG = Logger.getLogger(AppContextListener.class
			.getName());
	public static final String MONITOR = "arduino_monitor";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();

		try {

			String serialPort = servletContext.getInitParameter("SERIAL_PORT");
			Integer serialBaudRate = Integer.valueOf(servletContext
					.getInitParameter("SERIAL_BAUD_RATE"));
			Long serialThreadTime = Long.valueOf(servletContext
					.getInitParameter("SERIAL_THREAD_TIME"));

			servletContext.setAttribute(MONITOR, new ArduinoMonitor(serialPort,
					serialBaudRate, serialThreadTime));
		} catch (ArduinoException e) {
			LOG.log(Level.SEVERE, e.getMessage());
		}

		MonitorService monitor = (MonitorService) servletContext
				.getAttribute(MONITOR);
		if (monitor != null)
			try {
				monitor.init();
			} catch (ArduinoException e) {
				LOG.log(Level.WARNING, e.getMessage());
			}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		MonitorService monitor = (MonitorService) servletContext
				.getAttribute(MONITOR);

		if (monitor != null)
			try {
				if (monitor.isInitialized())
					monitor.close();
			} catch (ArduinoException e) {
				LOG.log(Level.SEVERE, e.getMessage());
			}

	}

}
