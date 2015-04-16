package com.mkyong.bo.impl;

import org.springframework.stereotype.Service;

import com.mkyong.bo.HelloWorldBo;

@Service
public class HelloWorldBoImpl implements HelloWorldBo {

	public String getHelloWorld() {
		return "JAX-WS + Spring!";
	}

}