Windows
    http://jlog.org/rxtx-win.html 

    Copie o arquivo rxtxSerial.dll para:

    C:\Program Files\Java\jdkx.x.x\bin, onde x.x.x é a versão do JDK, por exemplo C:\Program Files\Java\jdk1.6.40\bin;
    C:\Program Files\Java\jrex\bin,  onde x é a versão do JRE, por exemplo C:\Program Files\Java\jre7\bin;
    C:\Windows\System32;
    C:\Windows\SysWOW64 (caso sistema operacional 64-bits (x64)).

    Copie o arquivo RXTXcomm.jar para:

    C:\Program Files\Java\jre\jrex\lib\ext, onde x é a versão do JRE, por exemplo C:\Program Files\Java\jre7\lib\ext. 

Linux
    http://jlog.org/rxtx-lin.html 

    Copie o arquivo librxtxSerial.so para:   

    /usr/lib/, exemplo: cp /home/Usuario/librxtxSerial.so /usr/lib/.

    Copie o arquivo RXTXcomm.jar para:

    /usr/share/java/, exemplo.: cp /home/Usuario/RXTXcomm.jar /usr/share/java/.
	
Tomcat 8:
Altera a tag do arquivo 'server.xml', o arquivo esta localizado no diretorio 'conf' no tomcat8
<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" address="0.0.0.0"/>
<!--Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" address="0.0.0.0" proxyHost="200.141.106.145" proxyPort="8080" /-->

Incluir a tag no arquivo 'tomcat-users.xml', o arquivo esta localizado no diretorio 'conf' no tomcat8
<user username="tomcat" password="tomcat" roles="tomcat,manager-gui,admin-gui,manager-script"/>

	
	
