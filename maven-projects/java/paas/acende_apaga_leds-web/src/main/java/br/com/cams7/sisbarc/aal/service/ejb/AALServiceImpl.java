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
import javax.xml.ws.WebServiceRef;

import br.com.cams7.as.service.BaseServiceImpl;
import br.com.cams7.jpa.domain.BaseEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin.Evento;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin.Intervalo;
import br.com.cams7.sisbarc.aal.jpa.domain.pk.PinPK;
import br.com.cams7.sisbarc.aal.service.ws.AppArduinoService;
import br.com.cams7.sisbarc.aal.service.ws.AppArduinoServiceImplService;
import br.com.cams7.sisbarc.aal.service.ws.PinArray;
import br.com.cams7.sisbarc.aal.service.ws.PinPKArray;

/**
 * @author cams7
 *
 */
public abstract class AALServiceImpl<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseServiceImpl<E, ID> implements AALService<E, ID> {

	@PersistenceContext(unitName = "acendeApagaLEDsUnit")
	private EntityManager entityManager;

	@WebServiceRef(value = AppArduinoServiceImplService.class)
	private AppArduinoService monitor;

	public AALServiceImpl() {
		super();
	}

	@PostConstruct
	private void init() {
		getLog().info("Initialize EJB");

		Map<String, Object> context = ((BindingProvider) monitor)
				.getRequestContext();

		// 192.168.1.50:8080
		String url = (String) getEntityManager()
				.createNamedQuery("Usuario.buscaWSDLLocation")
				.setParameter("id", (short) 1).getSingleResult();
		if (url != null) {
			final String WSDL_LOCATION = "http://" + url
					+ "/acende_apaga_leds/aal_monitor";

			getLog().info("WSDL: " + WSDL_LOCATION);

			context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
					WSDL_LOCATION);
		}
	}

	@PreDestroy
	private void close() {
		getLog().info("Terminate EJB");
	}

	protected PinPKArray getIDs(List<E> entidades) {
		PinPKArray array = new PinPKArray();

		for (E entidade : entidades)
			array.getItem().add((PinPK) entidade.getId());

		return array;
	}

	protected PinArray getPinos(List<E> entidades) {
		PinArray array = new PinArray();

		for (E entidade : entidades)
			array.getItem().add((Pin) entidade);
		return array;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.sisbarc.aal.ejb.service.AALService#atualizaPino(br.com.cams7
	 * .sisbarc.aal.jpa.domain.Pin)
	 */
	@Asynchronous
	public Future<Boolean> atualizaPino(E entidade) {
		Pin pino = (Pin) entidade;

		PinPK id = pino.getId();
		Evento evento = pino.getEvento();
		Intervalo intervalo = pino.getIntervalo();

		evento = getMonitor().alteraEvento(id, evento, intervalo);

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
	public Future<Boolean> sincronizaEventos(List<E> entidades) {
		PinArray array = getMonitor().buscaDados(getIDs(entidades));

		Boolean arduinoRun = Boolean.TRUE;

		for (Pin p : array.getItem()) {
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
	public Future<Boolean> alteraEventos(List<E> entidades) {
		PinArray array = getMonitor().alteraEventos(getPinos(entidades));

		Boolean arduinoRun = Boolean.TRUE;

		for (Pin pino : array.getItem())
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

	protected AppArduinoService getMonitor() {
		return monitor;
	}

}
