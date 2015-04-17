package br.com.cams7.webapp.controler.rest;

import java.io.Serializable;
import java.util.logging.Level;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cams7.jpa.domain.BaseEntity;
import br.com.cams7.webapp.controler.BaseControler;
import br.com.cams7.webapp.service.BaseService;

public abstract class BaseRest<S extends BaseService<E, ID>, E extends BaseEntity<ID>, ID extends Serializable>
		extends BaseControler<S, E, ID> {

	public BaseRest() {
		super();
	}

	@ExceptionHandler({ Exception.class })
	public @ResponseBody ResponseEntity<?> handleException(Exception exception) {
		String errorMessage = exception.getMessage();

		ResponseEntity<?> response = new ResponseEntity<>(
				getHeaders(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		getLog().log(Level.SEVERE, errorMessage);

		return response;
	}

	protected HttpHeaders getHeaders(String message) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("errorMessage", message);
		return headers;
	}

}
