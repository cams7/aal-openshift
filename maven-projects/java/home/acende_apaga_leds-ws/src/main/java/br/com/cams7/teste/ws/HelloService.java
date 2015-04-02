package br.com.cams7.teste.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
//@SOAPBinding(style = Style.DOCUMENT)
public interface HelloService {

	@WebMethod
	public String printMessage(String userName);

}