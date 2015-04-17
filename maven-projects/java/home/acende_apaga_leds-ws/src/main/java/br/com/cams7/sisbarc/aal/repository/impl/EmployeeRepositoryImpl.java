package br.com.cams7.sisbarc.aal.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.cams7.sisbarc.aal.repository.EmployeeRepository;
import br.com.cams7.webapp.jpa.repository.WebRepositoryImpl;

import com.journaldev.jpa.data.Employee;

@Repository
public class EmployeeRepositoryImpl extends WebRepositoryImpl<Employee, String>
		implements EmployeeRepository {

	@PersistenceContext//(unitName = "acendeApagaLEDsUnit_NoSql")
	private EntityManager entityManager;

	public EmployeeRepositoryImpl() {
		super();
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
