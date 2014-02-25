package com.san.calculator.client;

import com.san.calculator.client.handler.CalculatorHandlerResolver;
import com.san.calculator.client.stub.Calculator;
import com.san.calculator.client.stub.Calculator_Service;

public class CalculatorClient {

    public static void main(String[] args) {
        Calculator_Service service = new Calculator_Service();
        service.setHandlerResolver(new CalculatorHandlerResolver());
        Calculator calculator = service.getCalculatorTestPortName11();

        System.out.println(calculator.addNumbers(1, 2));
    }
}
