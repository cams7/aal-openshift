/**
 * 
 */
package br.com.cams7.webapp.service;

import java.io.Serializable;

import br.com.cams7.jpa.domain.BaseEntity;
import br.com.cams7.webapp.jpa.repository.WebRepository;

/**
 * @author cesar
 *
 */
public interface BaseService<E extends BaseEntity<ID>, ID extends Serializable>
		extends WebRepository<E, ID> {

}
