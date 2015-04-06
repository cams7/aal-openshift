/**
 * 
 */
package br.com.cams7.sisbarc.aal;

//import java.util.Hashtable;
import java.util.logging.Level;

import br.com.cams7.arduino.ArduinoException;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin;
//import br.com.cams7.sisbarc.aal.ejb.service.AppWildflyService;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin.Evento;
import br.com.cams7.sisbarc.aal.jpa.domain.Pin.Intervalo;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.jpa.domain.pk.PinPK;
import br.com.cams7.sisbarc.aal.service.MonitorService;
import br.com.cams7.sisbarc.arduino.ArduinoServiceImpl;
import br.com.cams7.sisbarc.arduino.vo.Arduino;
import br.com.cams7.sisbarc.arduino.vo.Arduino.ArduinoEvent;
import br.com.cams7.sisbarc.arduino.vo.Arduino.ArduinoStatus;
import br.com.cams7.sisbarc.arduino.vo.ArduinoEEPROM;
import br.com.cams7.sisbarc.arduino.vo.ArduinoUSART;
import br.com.cams7.sisbarc.arduino.vo.EEPROMData;

/**
 * @author cams7
 *
 */
public class ArduinoMonitor extends ArduinoServiceImpl implements
		MonitorService {

	private final byte D13_LED_PISCA = 13; // Pino 13 Digital

	private final byte D11_LED_AMARELO = 11; // Pino 11 PWM
	private final byte D10_LED_VERDE = 10; // Pino 10 PWM
	private final byte D09_LED_VERMELHO = 9; // Pino 09 PWM
	private final byte D06_LED_AMARELO = 6; // Pino 06 PWM
	private final byte D05_LED_VERDE = 5; // Pino 05 PWM
	private final byte D04_LED_VERMELHO = 4; // Pino 04 Digital

	private final byte A0_POTENCIOMETRO = 0; // Pino 0 Analogico

	public ArduinoMonitor(String serialPort, int baudRate, long threadTime)
			throws ArduinoException {
		super(serialPort, baudRate, threadTime);
		getLog().info("Novo Servico");
	}

	@Override
	protected void receiveExecute(ArduinoPinType pinType, byte pin,
			short pinValue) {
		switch (pinType) {
		case DIGITAL: {
			switch (pin) {
			case D11_LED_AMARELO:
			case D10_LED_VERDE:
			case D09_LED_VERMELHO:
			case D06_LED_AMARELO:
			case D05_LED_VERDE:
			case D04_LED_VERMELHO: {
				getLog().info(
						"EXECUTE -> LED ("
								+ pin
								+ "): "
								+ (pinValue == 0x0001 ? EstadoLED.ACESO
										: EstadoLED.APAGADO));
				break;
			}
			case D13_LED_PISCA: {
				// getLog().info(
				// "USART -> LED Pisca ("
				// + pin
				// + "): "
				// + (pinValue == 0x0001 ? EstadoLED.ACESO
				// : EstadoLED.APAGADO));
				break;
			}
			default:
				break;
			}
			break;
		}
		case ANALOG: {
			switch (pin) {
			case A0_POTENCIOMETRO: {
				getLog().info(
						"EXECUTE -> Potenciometro (" + pin + "): " + pinValue);
				break;
			}
			default:
				break;
			}
			break;
		}
		default:
			break;
		}

	}

	@Override
	protected void receiveMessage(ArduinoPinType pinType, byte pin,
			short pinValue) {
		switch (pinType) {
		case DIGITAL: {
			switch (pin) {
			case D13_LED_PISCA:
			case D11_LED_AMARELO:
			case D10_LED_VERDE:
			case D09_LED_VERMELHO:
			case D06_LED_AMARELO:
			case D05_LED_VERDE:
			case D04_LED_VERMELHO: {
				getLog().info("MESSAGE -> LED (" + pin + "): " + pinValue);
				break;
			}
			default:
				break;
			}
			break;
		}
		case ANALOG: {
			switch (pin) {
			case A0_POTENCIOMETRO: {
				getLog().info(
						"MESSAGE -> Potenciometro (" + pin + "): " + pinValue);
				break;
			}
			default:
				break;
			}
			break;
		}
		default:
			break;
		}

	}

	@Override
	protected void receiveWrite(ArduinoPinType pinType, byte pin,
			byte threadInterval, byte actionEvent) {
		switch (pinType) {
		case DIGITAL: {
			switch (pin) {
			case D13_LED_PISCA:
			case D11_LED_AMARELO:
			case D10_LED_VERDE:
			case D09_LED_VERMELHO:
			case D06_LED_AMARELO:
			case D05_LED_VERDE:
			case D04_LED_VERMELHO: {
				getLog().info(
						"WRITE -> LED (" + pin + "): threadInterval = "
								+ threadInterval + ", actionEvent = "
								+ actionEvent);
				break;
			}
			default:
				break;
			}
			break;
		}
		case ANALOG: {
			switch (pin) {
			case A0_POTENCIOMETRO: {
				getLog().info(
						"WRITE -> Potenciometro (" + pin
								+ "): threadInterval = " + threadInterval
								+ ", actionEvent = " + actionEvent);
				break;
			}
			default:
				break;
			}
			break;
		}
		default:
			break;
		}

	}

	@Override
	protected void receiveRead(ArduinoPinType pinType, byte pin,
			byte threadInterval, byte actionEvent) {
		switch (pinType) {
		case DIGITAL: {
			switch (pin) {
			case D13_LED_PISCA:
			case D11_LED_AMARELO:
			case D10_LED_VERDE:
			case D09_LED_VERMELHO:
			case D06_LED_AMARELO:
			case D05_LED_VERDE:
			case D04_LED_VERMELHO: {
				getLog().info(
						"READ -> LED (" + pin + "): threadInterval = "
								+ threadInterval + ", actionEvent = "
								+ actionEvent);
				break;
			}
			default:
				break;
			}
			break;
		}
		case ANALOG: {
			switch (pin) {
			case A0_POTENCIOMETRO: {
				getLog().info(
						"READ -> Potenciometro (" + pin
								+ "): threadInterval = " + threadInterval
								+ ", actionEvent = " + actionEvent);
				break;
			}
			default:
				break;
			}
			break;
		}
		default:
			break;
		}

	}

	@Override
	protected short sendResponse(ArduinoPinType pinType, byte pin,
			short pinValue) {
		switch (pinType) {
		case DIGITAL: {
			switch (pin) {
			case D11_LED_AMARELO:
			case D10_LED_VERDE:
			case D09_LED_VERMELHO:
			case D06_LED_AMARELO:
			case D05_LED_VERDE:
			case D04_LED_VERMELHO: {
				pinValue = acendeOuApagaLEDPorBotao(pin, pinValue);
				break;
			}
			default:
				break;
			}
			break;
		}
		case ANALOG: {
			break;
		}
		default:
			break;
		}
		return pinValue;
	}

	@Override
	public EstadoLED alteraEstadoLED(PinPK pinoId, EstadoLED estado)
			throws ArduinoException {
		ArduinoPinType tipoPino = pinoId.getPinType();
		byte pinoLED = pinoId.getPin().byteValue();

		boolean estadoLED = (estado == EstadoLED.ACESO);

		if (tipoPino == ArduinoPinType.DIGITAL) {
			sendPinDigitalUSART(ArduinoStatus.SEND_RESPONSE, pinoLED, estadoLED);
			serialThreadTime();

			return getEstadoLED(pinoId, ArduinoEvent.EXECUTE);
		}

		return null;
	}

	@Override
	public LEDEntity[] buscaEstadoLEDs(PinPK[] ids) throws ArduinoException {
		for (PinPK pino : ids) {
			ArduinoPinType tipoPino = pino.getPinType();
			byte pinoLED = pino.getPin().byteValue();

			if (tipoPino == ArduinoPinType.DIGITAL)
				sendPinDigitalUSARTMessage(ArduinoStatus.SEND_RESPONSE, pinoLED);

		}

		serialThreadTime();

		LEDEntity[] leds = new LEDEntity[ids.length];
		for (short i = 0; i < ids.length; i++) {
			PinPK pino = ids[i];
			EstadoLED estado = getEstadoLED(pino, ArduinoEvent.MESSAGE);

			leds[i] = new LEDEntity(pino);
			leds[i].setEstado(estado);
		}

		return leds;
	}

	@Override
	public Evento alteraEvento(PinPK pinoId, Evento evento, Intervalo intervalo)
			throws ArduinoException {

		sendEEPROMWrite(ArduinoStatus.SEND_RESPONSE, pinoId.getPinType(),
				pinoId.getPin().byteValue(), (byte) intervalo.ordinal(),
				(byte) evento.ordinal());

		serialThreadTime();

		return getEvento(pinoId);
	}

	@Override
	public Pin[] alteraEventos(Pin[] pinos) throws ArduinoException {
		for (Pin pino : pinos) {
			PinPK id = pino.getId();
			sendEEPROMWrite(ArduinoStatus.SEND_RESPONSE, id.getPinType(), id
					.getPin().byteValue(),
					(byte) pino.getIntervalo().ordinal(), (byte) pino
							.getEvento().ordinal());
		}

		serialThreadTime();

		for (Pin pino : pinos)
			pino.setEvento(getEvento(pino.getId()));

		return pinos;
	}

	@Override
	public Pin[] buscaDados(PinPK[] ids) throws ArduinoException {
		for (PinPK id : ids)
			sendEEPROMRead(ArduinoStatus.SEND_RESPONSE, id.getPinType(), id
					.getPin().byteValue());

		serialThreadTime();

		Pin[] pinos = new Pin[ids.length];

		for (short i = 0; i < ids.length; i++) {
			PinPK id = ids[i];
			pinos[i] = new Pin(id) {
				private static final long serialVersionUID = 1L;
			};

			EEPROMData data = getDados(id);

			if (data != null) {
				pinos[i].setEvento(Evento.values()[data.getActionEvent()]);
				pinos[i].setIntervalo(Intervalo.values()[data
						.getThreadInterval()]);
			}
		}

		return pinos;
	}

	private Arduino getArduinoResponse(ArduinoEvent event, PinPK pinoId) {
		ArduinoPinType pinType = pinoId.getPinType();
		byte pin = pinoId.getPin().byteValue();

		String key = getKeyCurrentStatus(event, pinType, pin);

		if (getCurrentStatus().isEmpty()
				|| !getCurrentStatus().containsKey(key))
			return null;

		Arduino arduino = getCurrentStatus().get(key);

		if (arduino.getTransmitter() != Arduino.ArduinoTransmitter.ARDUINO)
			return null;

		if (arduino.getStatus() != ArduinoStatus.RESPONSE)
			return null;

		return arduino;
	}

	/**
	 * Obtem o ESTADO atual do LED informado,
	 * 
	 * Obs.: Os dados sao recebidos pela SERIAL do ARDUINO
	 * 
	 * @param PINO
	 *            do LED - Numero do PINO DIGITAL
	 * @return ESTADO do LED
	 */
	private EstadoLED getEstadoLED(PinPK pinoId, ArduinoEvent arduinoEvent) {
		if (arduinoEvent != ArduinoEvent.EXECUTE
				&& arduinoEvent != ArduinoEvent.MESSAGE)
			return null;

		Arduino arduino = getArduinoResponse(arduinoEvent, pinoId);
		if (arduino == null)
			return null;

		short estadoLED = ((ArduinoUSART) arduino).getPinValue();
		EstadoLED estado = null;
		switch (estadoLED) {
		case 0x0000:
			estado = EstadoLED.APAGADO;
			break;
		case 0x0001:
			estado = EstadoLED.ACESO;
			break;
		default:
			break;
		}
		return estado;
	}

	/**
	 * Obtem o EVENTO, os dados sao recebidos pela SERIAL do ARDUINO
	 * 
	 * @param PINO
	 *            - Numero do PINO DIGITAL/ANALOG
	 * @return EVENTO do LED
	 */
	private Evento getEvento(PinPK pinoId) {
		Arduino arduino = getArduinoResponse(Arduino.ArduinoEvent.WRITE, pinoId);
		if (arduino == null)
			return null;

		return Evento.values()[((ArduinoEEPROM) arduino).getActionEvent()];
	}

	/**
	 * Obtem os DADOS da EEPROM no ARDUINO
	 * 
	 * Obs.: Os dados sao recebidos pela SERIAL do ARDUINO
	 * 
	 * @param PINO
	 *            - Numero do PINO DIGITAL/ANALOGICO
	 * @return DADOS da EEPROM no ARDUINO
	 */
	private EEPROMData getDados(PinPK pino) {
		Arduino arduino = getArduinoResponse(Arduino.ArduinoEvent.READ, pino);
		if (arduino == null)
			return null;

		return (EEPROMData) arduino;
	}

	private short acendeOuApagaLEDPorBotao(byte pinoLED, short estadoPino) {
		if (estadoPino == 0x0000)
			return estadoPino;

		estadoPino = 0x0000;

		// try {
		// AppWildflyService service = lookupAppWildflyService();
		//
		// EstadoLED estado = service.getEstadoLEDAtivadoPorBotao(pinoLED);
		//
		// if (estado != null && estado == EstadoLED.ACESO)
		// estadoPino = (short) 0x0001;
		//
		// } catch (NamingException e) {
		// getLog().log(Level.SEVERE, e.getMessage());
		// }

		return estadoPino;
	}

	// private AppWildflyService lookupAppWildflyService() throws
	// NamingException {
	// final Hashtable<String, String> jndiProperties = new Hashtable<String,
	// String>();
	// jndiProperties.put(Context.URL_PKG_PREFIXES,
	// "org.jboss.ejb.client.naming");
	// final Context context = new InitialContext(jndiProperties);
	//
	// return (AppWildflyService) context
	// .lookup("ejb:sisbarc/acende_apaga_leds-ejb//LEDServiceImpl!"
	// + AppWildflyService.class.getName());
	//
	// }

	private void serialThreadTime() {
		try {
			Thread.sleep(getSerialThreadTime());
		} catch (InterruptedException e) {
			getLog().log(Level.WARNING, e.getMessage());
		}
	}

}
