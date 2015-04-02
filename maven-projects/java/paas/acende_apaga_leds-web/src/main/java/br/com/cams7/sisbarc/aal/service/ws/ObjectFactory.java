package br.com.cams7.sisbarc.aal.service.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import br.com.cams7.sisbarc.aal.jpa.domain.Pin;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.pk.PinPK;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the br.com.cams7.sisbarc.aal.ws package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _ArduinoException_QNAME = new QName(
			AppArduinoService.SEVICE_NAMESPACE, ArduinoException.EXCEPTION_NAME);

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: br.com.cams7.sisbarc.aal.ws
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link ArduinoException }
	 * 
	 */
	public ArduinoException createArduinoException() {
		return new ArduinoException();
	}

	/**
	 * Create an instance of {@link PinPK }
	 * 
	 */
	public PinPK createPinPK() {
		return new PinPK();
	}

	/**
	 * Create an instance of {@link PinPKArray }
	 * 
	 */
	public PinPKArray createPinPKArray() {
		return new PinPKArray();
	}

	/**
	 * Create an instance of {@link LedEntity }
	 * 
	 */
	public LEDEntity createLedEntity() {
		return new LEDEntity();
	}

	/**
	 * Create an instance of {@link LEDEntityArray }
	 * 
	 */
	public LEDEntityArray createLedEntityArray() {
		return new LEDEntityArray();
	}

	public Pin createPin() {
		return new Pin();
	}

	/**
	 * Create an instance of {@link PinArray }
	 * 
	 */
	public PinArray createPinArray() {
		return new PinArray();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ArduinoException }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = AppArduinoService.SEVICE_NAMESPACE, name = ArduinoException.EXCEPTION_NAME)
	public JAXBElement<ArduinoException> createArduinoException(
			ArduinoException value) {
		return new JAXBElement<ArduinoException>(_ArduinoException_QNAME,
				ArduinoException.class, null, value);
	}

}
