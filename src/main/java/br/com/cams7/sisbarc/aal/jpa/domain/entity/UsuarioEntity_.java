package br.com.cams7.sisbarc.aal.jpa.domain.entity;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UsuarioEntity.class)
public class UsuarioEntity_ {
	public static volatile SingularAttribute<UsuarioEntity, Short> id;
	public static volatile SingularAttribute<UsuarioEntity, String> nome;
	public static volatile SingularAttribute<UsuarioEntity, Date> cadastro;
	public static volatile SingularAttribute<UsuarioEntity, Boolean> ativo;
	public static volatile SingularAttribute<UsuarioEntity, String> wsdlLocation;
}
