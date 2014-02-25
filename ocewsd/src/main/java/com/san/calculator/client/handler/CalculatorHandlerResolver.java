package com.san.calculator.client.handler;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.util.ArrayList;
import java.util.List;

public class CalculatorHandlerResolver implements HandlerResolver {
    @Override
    public List<Handler> getHandlerChain(PortInfo portInfo) {
        List<Handler> handlers = new ArrayList<Handler>();
        handlers.add(new CalculatorHandler());

        return handlers;
    }
}
