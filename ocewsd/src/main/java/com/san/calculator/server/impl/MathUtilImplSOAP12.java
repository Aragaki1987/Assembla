package com.san.calculator.server.impl;

import com.san.calculator.server.MathUtil;

import javax.jws.WebService;

@WebService(endpointInterface = "com.san.calculator.server.MathUtil", targetNamespace = "http://www.san.com/calculator",
        serviceName = "Calculator12", portName = "CalculatorTestPortName12")
public class MathUtilImplSOAP12 implements MathUtil {

    @Override
    public Integer addNumbers(int arg0, int arg1) {
        return arg0 + arg1;
    }
}
