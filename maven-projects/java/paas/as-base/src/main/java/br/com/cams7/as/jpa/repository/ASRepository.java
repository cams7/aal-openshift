/**
 * 
 */
package br.com.cams7.as.jpa.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.metamodel.SingularAttribute;

import br.com.cams7.app.jpa.repository.BaseRepository;
import br.com.cams7.jpa.domain.BaseEntity;
import br.com.cams7.jpa.domain.SortOrderField;

/**
 * @author cesar
 *
 */
public interface ASRepository<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseRepository<E, ID> {

	@SuppressWarnings("unchecked")
	public long count(
			SingularAttribute<? extends BaseEntity<?>, ? extends BaseEntity<?>>... joins);

	@SuppressWarnings("unchecked")
	public long count(
			Map<String, Object> filters,
			SingularAttribute<? extends BaseEntity<?>, ? extends BaseEntity<?>>... joins);

	@SuppressWarnings("unchecked")
	public List<E> search(
			short first,
			byte pageSize,
			String sortField,
			SortOrderField sortOrder,
			Map<String, Object> filters,
			SingularAttribute<? extends BaseEntity<?>, ? extends BaseEntity<?>>... joins);

}
