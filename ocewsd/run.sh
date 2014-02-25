#!/bin/sh
clear
echo =========================== Build by Maven ===================================
mvn clean install
echo =========================== Undeploying Calculator ===========================
asadmin undeploy Calculator
echo =========================== Deploying Calculator =============================
asadmin deploy ./target/Calculator.war
echo =========================== Generating Stub ==================================
wsimport -d ./target/ -p com.san.calculator.client.stub -s ./src/main/java/ http://localhost:8080/Calculator/Calculator?wsdl
echo =========================== FINISH ===========================================

