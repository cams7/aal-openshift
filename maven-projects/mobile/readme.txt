For Linux QBEX: 
	cd ~/desenv/git/github/paas/openshift/sisbarc/mobile/acende_apaga_leds-android

For Windows VAIO:
	cd C:\Users\cams7\desenv\github\paas\openshift\sisbarc\maven-projects\mobile\acende_apaga_leds-android
	
mvn clean install

mvn android:emulator-start
mvn android:deploy
mvn android:undeploy

android avd
emulator -avd API_10


