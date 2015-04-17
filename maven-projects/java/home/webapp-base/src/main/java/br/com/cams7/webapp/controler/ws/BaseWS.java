package br.com.cams7.webapp.controler.ws;

import java.io.Serializable;

import br.com.cams7.jpa.domain.BaseEntity;
import br.com.cams7.webapp.controler.BaseControler;
import br.com.cams7.webapp.service.BaseService;

public abstract class BaseWS<S extends BaseService<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseControler<S, E, ID> {

	public BaseWS() {
		super();
	}

}
