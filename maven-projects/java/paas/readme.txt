For Linux QBEX:  
	WildFly
	$JBOSS_HOME/bin/standalone.sh -b 192.168.1.8 -bmanagement 192.168.1.8
	
	Jboss-AS
	$EAP_HOME/bin/standalone.sh -b 192.168.1.8 -bmanagement 192.168.1.8

For Windows VAIO: 
	#FOR /F "tokens=4 delims= " %%P IN ('netstat -a -n -o ^| findstr :8080') DO @ECHO TaskKill.exe /PID %%P
	#FOR /F "tokens=4 delims= " %%P IN ('netstat -a -n -o ^| findstr :9990') DO @ECHO TaskKill.exe /PID %%P
	
	netstat -a -n -o
	TaskKill /F /PID pid
	
	WildFly
	cd C:\Users\cams7\desenv\java\jboss\wildfly-8.2.0.Final_maven\bin
	standalone.bat -b 192.168.1.50 -bmanagement 192.168.1.50
	standalone.bat -b 0.0.0.0 -bmanagement 0.0.0.0
	
	Jboss-AS
	cd C:\Users\cams7\desenv\java\jboss\jboss-eap-6.3\bin
	standalone.bat -b 192.168.1.50 -bmanagement 192.168.1.50
	standalone.bat -c standalone-full.xml -b 192.168.1.50 -bmanagement 192.168.1.50

WildFly started
	mvn clean install wildfly:deploy -Dwildfly.port=10090
	mvn wildfly:undeploy -Dwildfly.port=10090

	mvn clean test -Parq-wildfly-remote

	For Linux QBEX: 
		mvn clean install wildfly:deploy -Dwildfly.hostname=192.168.1.8 -Dwildfly.port=10090
		mvn wildfly:undeploy -Dwildfly.hostname=192.168.1.8 -Dwildfly.port=10090
		
	For Windows VAIO:
		mvn clean install wildfly:deploy -Dwildfly.hostname=192.168.1.50 -Dwildfly.port=10090
		mvn wildfly:undeploy -Dwildfly.hostname=192.168.1.50 -Dwildfly.port=10090
	
WildFly stopped
	mvn clean test -Parq-wildfly-managed
	
Jboss-AS started
	mvn clean install jboss-as:deploy -Djboss-as.port=10090
	mvn jboss-as:undeploy -Djboss-as.port=10090

	mvn clean test -Parq-jbossas-remote

	For Linux QBEX: 
		mvn clean install jboss-as:deploy -Djboss-as.hostname=192.168.1.8 -Djboss-as.port=10090
		mvn jboss-as:undeploy -Djboss-as.hostname=192.168.1.8 -Djboss-as.port=10090
		
	For Windows VAIO:
		mvn clean install jboss-as:deploy -Djboss-as.hostname=192.168.1.50 -Djboss-as.port=10090
		mvn jboss-as:undeploy -Djboss-as.hostname=192.168.1.50 -Djboss-as.port=10090
	
Jboss-AS stopped
	mvn clean test -Parq-jbossas-managed
	
	
Create use EJB
cd C:\Users\cams7\desenv\java\jboss\wildfly-8.2.0.Final\bin
add-user.bat	
	b) Application User
	Username: AcendeApagaLEDs
    Password: abc@12345
    Roles:

