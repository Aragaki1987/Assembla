#!/bin/sh
clear
echo ============================================ STOP SERVER ==========================================================
sh /opt/Environment/apache-tomcat-7.0.52/bin/shutdown.sh
echo ============================================ BUILD PROJECT ========================================================
mvn clean install
echo ============================================ START SERVER =========================================================
cp ./target/TomcatAuthen.war /opt/Environment/apache-tomcat-7.0.52/webapps/TomcatAuthen.war
sh /opt/Environment/apache-tomcat-7.0.52/bin/startup.sh
echo ============================================ GENERATE ARTIFACT ====================================================
wsimport -s ./src/main/java/ -p com.san.test.stub -d ./target/classes/ ./src/main/webapp/WEB-INF/wsdl/TomcatAuthen.wsdl
echo ============================================ FINISH ===============================================================