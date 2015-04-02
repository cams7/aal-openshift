package br.com.cams7.sisbarc.aal.jpa.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

//import org.hibernate.validator.constraints.NotEmpty;

import br.com.cams7.jpa.domain.BaseEntity;

@Entity
@Table(name = "usuario")
@NamedQueries({
		@NamedQuery(name = " Usuario.findAll", query = "SELECT u FROM  UsuarioEntity u"),
		@NamedQuery(name = "Usuario.buscaWSDLLocation", query = "SELECT u.wsdlLocation FROM UsuarioEntity u WHERE u.id=:id") })
public class UsuarioEntity extends BaseEntity<Short> {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
	@Column(name = "id_usuario")
	private Short id;

	// @NotEmpty
	@Size(min = 3, max = 30)
	@Column(name = "nome_usuario", nullable = false)
	private String nome;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	private Date cadastro;

	@Column(name = "usuario_ativo", nullable = false)
	private boolean ativo;

	// @NotEmpty
	@Size(min = 12, max = 100)
	@Column(name = "monitor_url", nullable = false)
	private String wsdlLocation;

	public UsuarioEntity() {
		super();
	}

	public UsuarioEntity(Short id) {
		this();

		this.id = id;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[id = " + getId()
				+ ", nome = " + getNome() + ", cadastro = " + getCadastro()
				+ ", ativo = " + isAtivo() + "]";
	}

	@Override
	public Short getId() {
		return id;
	}

	@Override
	public void setId(Short id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getWsdlLocation() {
		return wsdlLocation;
	}

	public void setWsdlLocation(String wsdlLocation) {
		this.wsdlLocation = wsdlLocation;
	}

}
