package br.com.cams7.app.jpa.repository;

import java.io.Serializable;
import java.util.List;

import br.com.cams7.jpa.domain.BaseEntity;

public interface BaseRepository<E extends BaseEntity<ID>, ID extends Serializable> {

	public void save(E entity);

	public E update(E entity);

	public void update(List<E> entities);

	// public void remove(E entity);

	public E remove(ID id);

	public E findOne(ID id);

	public List<E> findAll();

	public List<E> findRange(int[] range);

	// public long count();

}
