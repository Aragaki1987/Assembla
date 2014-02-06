#!/bin/sh
clear
echo =========================== Build by Maven ===================================
mvn clean install
echo =========================== Undeploying Calculator ===========================
asadmin undeploy Calculator
echo =========================== Deploying Calculator =============================
asadmin deploy ./target/Calculator.war
echo =========================== FINISH ===========================================

