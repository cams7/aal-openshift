/**
 * 
 */
package br.com.cams7.as.service;

import java.io.Serializable;

import br.com.cams7.as.jpa.repository.ASRepositoryImpl;
import br.com.cams7.jpa.domain.BaseEntity;

/**
 * @author cesar
 *
 */
public abstract class BaseServiceImpl<E extends BaseEntity<ID>, ID extends Serializable>
		extends ASRepositoryImpl<E, ID> implements BaseService<E, ID> {

	public BaseServiceImpl() {
		super();
	}

}
