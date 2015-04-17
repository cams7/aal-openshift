package br.com.cams7.webapp.jpa.repository;

import java.io.Serializable;

import br.com.cams7.app.jpa.repository.BaseRepositoryImpl;
import br.com.cams7.jpa.domain.BaseEntity;

public abstract class WebRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseRepositoryImpl<E, ID> implements WebRepository<E, ID> {

	public WebRepositoryImpl() {
		super();
	}

}
