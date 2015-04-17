/**
 * 
 */
package br.com.cams7.webapp.service;

import java.io.Serializable;
import java.util.List;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.app.jpa.repository.BaseRepository;
import br.com.cams7.jpa.domain.BaseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cesar
 *
 */
public abstract class BaseServiceImpl<R extends BaseRepository<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends AbstractBase<E> implements BaseService<E, ID> {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;

	@Autowired
	private R repository;

	public BaseServiceImpl() {
		super();
	}

	@Transactional
	public void save(E entity) {
		getRepository().save(entity);
	}

	@Transactional
	public E update(E entity) {
		entity = getRepository().update(entity);
		return entity;
	}

	@Transactional
	public void update(List<E> entities) {
		getRepository().update(entities);
	}

	@Transactional
	public E remove(ID id) {
		return getRepository().remove(id);
	}

	@Transactional(readOnly = true)
	public E findOne(ID id) {
		E entity = getRepository().findOne(id);
		return entity;
	}

	@Transactional(readOnly = true)
	public List<E> findAll() {
		List<E> entities = getRepository().findAll();
		return entities;
	}

	@Transactional(readOnly = true)
	public List<E> findRange(int[] range) {
		List<E> entities = getRepository().findRange(range);
		return entities;
	}

	@Override
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

	protected R getRepository() {
		return repository;
	}

}
