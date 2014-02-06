package com.san.calculator.server;

import javax.jws.WebService;

@WebService(endpointInterface = "com.san.calculator.server.MathUtil", targetNamespace = "http://www.san.com/calculator",
        serviceName = "Calculator", portName = "CalculatorTestPortName12")
public class MathUtilImplSOAP12 {

    public int addNumbers(int arg0, int arg1) {
        return arg0 + arg1;
    }
}
