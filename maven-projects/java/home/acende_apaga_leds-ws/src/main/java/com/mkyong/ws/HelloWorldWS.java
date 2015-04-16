package com.mkyong.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mkyong.bo.HelloWorldBo;

@Controller("helloWs")
@WebService
public class HelloWorldWS {

	@Autowired
	private HelloWorldBo helloWorldBo;

	@WebMethod(exclude = true)
	public void setHelloWorldBo(HelloWorldBo helloWorldBo) {
		this.helloWorldBo = helloWorldBo;
	}

	@WebMethod(operationName = "getHelloWorld")
	public String getHelloWorld() {

		return helloWorldBo.getHelloWorld();

	}

}