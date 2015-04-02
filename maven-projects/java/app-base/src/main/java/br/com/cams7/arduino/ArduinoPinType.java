/**
 * 
 */
package br.com.cams7.arduino;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * @author cams7
 *
 */
@XmlType(name = "arduinoPinType")
@XmlEnum
public enum ArduinoPinType {
	DIGITAL('d'), // Porta Digital
	ANALOG('a'); // Porta Analogica

	private char abbreviation;

	private ArduinoPinType(char abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String value() {
		return name();
	}

	public static ArduinoPinType fromValue(String value) {
		return valueOf(value);
	}

	public char getAbbreviation() {
		return abbreviation;
	}

}
