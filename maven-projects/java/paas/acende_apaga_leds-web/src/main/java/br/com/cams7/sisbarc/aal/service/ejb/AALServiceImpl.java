/**
 * 
 */
package br.com.cams7.sisbarc.aal.service.ejb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.BindingProvider;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.as.service.BaseServiceImpl;
import br.com.cams7.jpa.domain.BaseEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin.Evento;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin.Intervalo;
import br.com.cams7.sisbarc.aal.jpa.domain.pk.PinPK;
import br.com.cams7.sisbarc.aal.service.MonitorService;
import br.com.cams7.sisbarc.aal.ws.MonitorServiceImplService;

/**
 * @author cams7
 *
 */
public abstract class AALServiceImpl<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseServiceImpl<E, ID> implements AALService<E, ID> {

	@PersistenceContext(unitName = "acendeApagaLEDsUnit")
	private EntityManager entityManager;

	public AALServiceImpl() {
		super();
	}

	@PostConstruct
	private void init() {
		getLog().info("Initialize EJB");
	}

	@PreDestroy
	private void close() {
		getLog().info("Terminate EJB");
	}

	protected PinPK[] getIDs(List<E> entidades) {
		PinPK[] ids = new PinPK[entidades.size()];

		for (short i = 0; i < entidades.size(); i++)
			ids[i] = (PinPK) entidades.get(i).getId();

		return ids;
	}

	protected Pin[] getPinos(List<E> entidades) {
		Pin[] pinos = new Pin[entidades.size()];

		for (short i = 0; i < entidades.size(); i++)
			pinos[i] = (Pin) entidades.get(i);

		return pinos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.ejb.service.AALService#atualizaPino(br.com.cams7
	 * .sisbarc.aal.jpa.domain.Pin)
	 */
	@Asynchronous
	public Future<Boolean> atualizaPino(E entidade) throws ArduinoException {
		Pin pino = (Pin) entidade;

		Evento evento = getMonitor().alteraEvento(pino.getId(),
				pino.getEvento(), pino.getIntervalo());

		Boolean arduinoRun = Boolean.FALSE;

		if (evento != null) {
			pino.setEvento(evento);
			save(entidade);
			arduinoRun = Boolean.TRUE;

			getLog().info(
					"O evento do PINO '" + pino.getId() + "' foi alterado '"
							+ pino.getEvento() + "'");
		} else
			getLog().log(
					Level.WARNING,
					"Ocorreu um erro ao tenta buscar o EVENTO do PINO '"
							+ pino.getId() + "'");

		return new AsyncResult<Boolean>(arduinoRun);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.ejb.service.AALService#sincronizaEventos(java
	 * .util.List)
	 */
	@Asynchronous
	public Future<Boolean> sincronizaEventos(List<E> entidades)
			throws ArduinoException {
		Pin[] pinos = getMonitor().buscaDados(getIDs(entidades));

		Boolean arduinoRun = Boolean.TRUE;

		for (Pin p : pinos) {
			if (p.getEvento() == null && p.getIntervalo() == null) {
				arduinoRun = Boolean.FALSE;
				break;
			}

			PinPK id = p.getId();
			Evento evento = p.getEvento();
			Intervalo intervalo = p.getIntervalo();

			for (E entidade : entidades) {
				Pin pino = (Pin) entidade;
				if (id.equals((PinPK) entidade.getId())) {
					pino.setEvento(evento);
					pino.setIntervalo(intervalo);
					break;
				}
			}

		}

		if (arduinoRun) {
			update(entidades);
			getLog().info("Os EVENTOs dos PINOs foram sincronizados");
		} else
			getLog().log(Level.WARNING,
					"Ocorreu um erro ao tenta buscar os DADOs dos PINOs");

		return new AsyncResult<Boolean>(arduinoRun);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.ejb.service.AALService#alteraEventos(java.util
	 * .List)
	 */
	@Asynchronous
	public Future<Boolean> alteraEventos(List<E> entidades)
			throws ArduinoException {
		Pin[] pinos = getMonitor().alteraEventos(getPinos(entidades));

		Boolean arduinoRun = Boolean.TRUE;

		for (Pin pino : pinos)
			if (pino.getEvento() == null) {
				arduinoRun = Boolean.FALSE;
				break;
			}

		if (arduinoRun)
			getLog().info("Os EVENTOs dos PINOs foram alterados");
		else
			getLog().log(Level.WARNING,
					"Ocorreu um erro ao tenta buscar os EVENTOs dos PINOs");

		return new AsyncResult<Boolean>(arduinoRun);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected MonitorService getMonitor() {
		MonitorService service = (new MonitorServiceImplService())
				.getMonitorServiceImplPort();

		Map<String, Object> context = ((BindingProvider) service)
				.getRequestContext();

		// 192.168.1.50:8080
		String url = (String) getEntityManager()
				.createNamedQuery("Usuario.buscaWSDLLocation")
				.setParameter("id", (short) 1).getSingleResult();
		if (url != null) {
			final String WSDL_LOCATION = url
					+ "/acende_apaga_leds/monitor?wsdl";

			getLog().info("WSDL: " + WSDL_LOCATION);

			context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
					WSDL_LOCATION);
		}
		return service;
	}

}
