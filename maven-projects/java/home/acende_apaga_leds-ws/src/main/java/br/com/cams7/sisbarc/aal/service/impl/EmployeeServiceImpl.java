package br.com.cams7.sisbarc.aal.service.impl;

import org.springframework.stereotype.Service;

import br.com.cams7.sisbarc.aal.repository.EmployeeRepository;
import br.com.cams7.sisbarc.aal.service.EmployeeService;
import br.com.cams7.webapp.service.BaseServiceImpl;

import com.journaldev.jpa.data.Employee;

@Service
public class EmployeeServiceImpl extends
		BaseServiceImpl<EmployeeRepository, Employee, String> implements
		EmployeeService {

	public EmployeeServiceImpl() {
		super();
	}

}
