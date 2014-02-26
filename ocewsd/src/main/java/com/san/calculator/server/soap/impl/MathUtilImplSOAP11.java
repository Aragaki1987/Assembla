package com.san.calculator.server.soap.impl;

import com.san.calculator.server.soap.MathUtil;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "com.san.calculator.server.soap.MathUtil", targetNamespace = "http://www.san.com/calculator",
        serviceName = "Calculator", portName = "CalculatorTestPortName11")
public class MathUtilImplSOAP11 implements MathUtil {

    @Resource
    protected WebServiceContext context;

    @Override
    public Integer addNumbers(int arg0, int arg1) {
        if(authenticate())
            return arg0 + arg1;
        return null;
    }

    private boolean authenticate() {
        MessageContext messageContext = context.getMessageContext();

        Map header = (Map) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);

        List<String> userList = (List<String>) header.get("Username");
        List<String> passList = (List<String>) header.get("Password");

        String username = "";
        String password = "";

        if(userList != null && userList.size() > 0)
            username = userList.get(0).toString();

        if(passList != null && passList.size() > 0)
            password = passList.get(0).toString();

        if(username.equals("San") && password.equals("123456")) {
            return true;
        }

        return false;
    }
}
