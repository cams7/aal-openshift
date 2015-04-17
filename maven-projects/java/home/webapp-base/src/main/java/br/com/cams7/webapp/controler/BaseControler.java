package br.com.cams7.webapp.controler;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.jpa.domain.BaseEntity;
import br.com.cams7.webapp.service.BaseService;

public abstract class BaseControler<S extends BaseService<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends AbstractBase<E> {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;

	@Autowired
	private S service;

	public BaseControler() {
		super();
	}

	@Override
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

	protected S getService() {
		return service;
	}

}
