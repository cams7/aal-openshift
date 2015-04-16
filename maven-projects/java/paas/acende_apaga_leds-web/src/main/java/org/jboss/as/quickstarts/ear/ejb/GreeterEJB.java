/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.ear.ejb;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;
import javax.xml.ws.handler.MessageContext;

import org.jboss.as.quickstarts.ear.ws.HelloServiceImplService;

import br.com.cams7.teste.ws.HelloService;

/**
 * A simple Hello World EJB. The EJB does not use an interface.
 * 
 * @author paul.robinson@redhat.com, 2011-12-21
 */
@Stateless
public class GreeterEJB {
	private final String IP = "localhost";

	@WebServiceRef(value = HelloServiceImplService.class)
	private HelloService port;

	/**
	 * This method takes a name and returns a personalised greeting.
	 * 
	 * @param name
	 *            the name of the person to be greeted
	 * @return the personalised greeting.
	 */
	public String sayHello(String name) {
		Map<String, Object> context = ((BindingProvider) port)
				.getRequestContext();

		String appPort = "8080";

		switch (name.toLowerCase()) {
		case "leandro":
			appPort = "8383";
			break;
		case "vbox":
			appPort = "8081";
			break;
		default:
			break;
		}

		String path = "http://" + IP + ":" + appPort
				+ "/acende_apaga_leds/sayhello";

		context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, path);

		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		headers.put("username", Collections.singletonList("ceanma@gmail.com"));
		headers.put("password", Collections.singletonList("teste@12345"));
		context.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

		String message = port.printMessage(name);
		return message;
	}
}
