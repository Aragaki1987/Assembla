package com.san.calculator.server;

import javax.annotation.security.RolesAllowed;
import javax.jws.WebService;

@WebService(endpointInterface = "com.san.calculator.server.MathUtil", targetNamespace = "http://www.san.com/calculator",
        serviceName = "Calculator", portName = "CalculatorTestPortName11")
public class MathUtilImplSOAP11 implements MathUtil {

    @Override
    @RolesAllowed("basicUser")
    public int addNumbers(int arg0, int arg1) {
        return arg0 + arg1;
    }
}