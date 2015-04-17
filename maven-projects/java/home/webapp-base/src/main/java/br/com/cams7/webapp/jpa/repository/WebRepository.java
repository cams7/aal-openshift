package br.com.cams7.webapp.jpa.repository;

import java.io.Serializable;

import br.com.cams7.app.jpa.repository.BaseRepository;
import br.com.cams7.jpa.domain.BaseEntity;

public interface WebRepository<E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseRepository<E, ID> {

}
