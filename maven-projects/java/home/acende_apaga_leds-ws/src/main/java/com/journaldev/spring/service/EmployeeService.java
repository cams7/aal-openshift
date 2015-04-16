package com.journaldev.spring.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journaldev.jpa.data.Employee;

@Service
public class EmployeeService {
	@PersistenceContext(unitName = "acendeApagaLEDsUnit_NoSql")
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	@Transactional
	public void register(Employee emp) {
		// Save employee
		getEm().persist(emp);
	}

	@Transactional(readOnly = true)
	public Employee findById(String id) {
		return getEm().find(Employee.class, id);
	}

	@Transactional(readOnly = true)
	public List<Employee> findByAll() {
		@SuppressWarnings("unchecked")
		List<Employee> list = getEm().createQuery("select e from Employee e")
				.getResultList();
		return list;
	}

	@Transactional
	public void remove(String id) {
		getEm().remove(id);
	}

}
