package br.com.cams7.sisbarc.aal.jpa.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import br.com.cams7.arduino.ArduinoPinType;
import br.com.cams7.jpa.domain.BaseEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.pk.PinPK;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pin", propOrder = { "id", "evento", "alteraEvento",
		"intervalo", "alteraIntervalo" })
@XmlSeeAlso({ LEDEntity.class })
@MappedSuperclass
public/* abstract */class Pin extends BaseEntity<PinPK> {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PinPK id;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "evento")
	private Evento evento;

	@Column(name = "altera_evento", nullable = false)
	private boolean alteraEvento;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "evento_intervalo")
	private Intervalo intervalo;

	@Column(name = "altera_intervalo", nullable = false)
	private boolean alteraIntervalo;

	public Pin() {
		super();
	}

	public Pin(PinPK pin) {
		super(pin);
	}

	public Pin(ArduinoPinType pinType, Short pin) {
		this(new PinPK(pinType, pin));
	}

	/**
	 * @return the id
	 */
	// @XmlJavaTypeAdapter(PinIDAdapter.class)
	public PinPK getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(PinPK id) {
		this.id = id;
	}

	/**
	 * @return the evento
	 */
	public Evento getEvento() {
		return evento;
	}

	/**
	 * @param evento
	 *            the evento to set
	 */
	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	/**
	 * @return the alteraEvento
	 */
	public boolean isAlteraEvento() {
		return alteraEvento;
	}

	/**
	 * @param alteraEvento
	 *            the alteraEvento to set
	 */
	public void setAlteraEvento(boolean alteraEvento) {
		this.alteraEvento = alteraEvento;
	}

	/**
	 * @return the intervalo
	 */
	public Intervalo getIntervalo() {
		return intervalo;
	}

	/**
	 * @param intervalo
	 *            the intervalo to set
	 */
	public void setIntervalo(Intervalo intervalo) {
		this.intervalo = intervalo;
	}

	/**
	 * @return the alteraIntervalo
	 */
	public boolean isAlteraIntervalo() {
		return alteraIntervalo;
	}

	/**
	 * @param alteraIntervalo
	 *            the alteraIntervalo to set
	 */
	public void setAlteraIntervalo(boolean alteraIntervalo) {
		this.alteraIntervalo = alteraIntervalo;
	}

	@XmlType(name = "evento")
	@XmlEnum
	public enum Evento {
		ACENDE_APAGA, // Acende ou apaga
		PISCA_PISCA, // Pisca-pisca
		FADE, // Acende ao poucos
		NENHUM; // Não faz nada

		public String value() {
			return name();
		}

		public static Evento fromValue(String value) {
			return valueOf(value);
		}
	}

	@XmlType(name = "intervalo")
	@XmlEnum
	public enum Intervalo {
		INTERVALO_10MILISEGUNDOS, // 1/100 de segundo
		INTERVALO_50MILISEGUNDOS, // 1/20 de segundo
		INTERVALO_100MILISEGUNDOS, // 1/10 de segundo
		INTERVALO_1SEGUNDO, // 1 segundo
		INTERVALO_3SEGUNDOS, // 3 segundos
		INTERVALO_5SEGUNDOS, // 5 segundos
		INTERVALO_10SEGUNDOS, // 10 segundos
		SEM_INTERVALO; // O evento sera apenas executado quando for chamado

		public String value() {
			return name();
		}

		public static Intervalo fromValue(String value) {
			return valueOf(value);
		}
	}

}
