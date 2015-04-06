package br.com.cams7.ws;

/**
 * The MyServiceFault class.
 * 
 * @author Binu George
 * @since 2013
 * @version 1.0
 */
public class ServiceFault {
	/**
	 * Fault Code
	 */
	private String faultCode;
	/**
	 * Fault String
	 */
	private String faultString;

	public ServiceFault() {
		super();
	}

	public ServiceFault(String faultString) {
		super();

		setFaultString(faultString);
	}

	public ServiceFault(String faultCode, String faultString) {
		super();

		setFaultCode(faultCode);
		setFaultString(faultString);
	}

	/**
	 * @return the faultCode
	 */
	public String getFaultCode() {
		return faultCode;
	}

	/**
	 * @param faultCode
	 *            the faultCode to set
	 */
	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	/**
	 * @return the faultString
	 */
	public String getFaultString() {
		return faultString;
	}

	/**
	 * @param faultString
	 *            the faultString to set
	 */
	public void setFaultString(String faultString) {
		this.faultString = faultString;
	}

}
