package br.com.cams7.teste.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = HelloService.WEBSERVICE_NAME, targetNamespace = HelloService.WEBSERVICE_TARGETNAMESPACE)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloService {

	public static final String WEBSERVICE_NAME = "HelloService";
	public static final String WEBSERVICE_TARGETNAMESPACE = "http://ws.teste.cams7.com.br/";

	@WebMethod
	// @WebResult(partName = "return")
	// @Action(input =
	// "http://ws.teste.cams7.com.br/HelloService/printMessageRequest", output =
	// "http://ws.teste.cams7.com.br/HelloService/printMessageResponse")
	public String printMessage(
	/* @WebParam(name = "mensagemID", partName = "mensagem") */String message);

}