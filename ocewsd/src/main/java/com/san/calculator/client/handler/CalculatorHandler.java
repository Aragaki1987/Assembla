package com.san.calculator.client.handler;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.*;

public class CalculatorHandler implements SOAPHandler<SOAPMessageContext> {
    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Map<String, List<String>> header = new HashMap<String, List<String>>();

        header.put("Username", Collections.singletonList("San"));
        header.put("Password", Collections.singletonList("123456"));

        context.put(SOAPMessageContext.HTTP_REQUEST_HEADERS, header);

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
