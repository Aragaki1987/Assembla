package com.san.calculator.server.rest.impl;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import javax.xml.ws.http.HTTPException;
import java.io.ByteArrayInputStream;

@WebServiceProvider(portName = "teamport",
        serviceName = "teamservice",
        targetNamespace = "http://san.com.vn/team")
@ServiceMode(value = Service.Mode.PAYLOAD)
@BindingType(value = HTTPBinding.HTTP_BINDING)
public class TeamManagement implements Provider<Source> {
    @Resource
    protected WebServiceContext ws_ctx;

    @Override
    public Source invoke(Source request) {
        if (ws_ctx == null) throw new RuntimeException("DI failed on ws_ctx.");
        // Grab the message context and extract the request verb.
        MessageContext msg_ctx = ws_ctx.getMessageContext();
        String http_verb = (String)msg_ctx.get(MessageContext.HTTP_REQUEST_METHOD);
        http_verb = http_verb.trim().toUpperCase();
        // Act on the verb. To begin, only GET requests accepted.
        if (http_verb.equals("GET")) return doGet(msg_ctx);
        else throw new HTTPException(405); // method not allowed
    }

    private Source doGet(MessageContext msg_ctx) {
        // Parse the query string.
        String query_string = (String) msg_ctx.get(MessageContext.QUERY_STRING);
        if (query_string == null)
            return new StreamSource(new ByteArrayInputStream("Query String is null".getBytes()));
        else {
            return new StreamSource(new ByteArrayInputStream("Query String is not null".getBytes()));
        }
    }
}
