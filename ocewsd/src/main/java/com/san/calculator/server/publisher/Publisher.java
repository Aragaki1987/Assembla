package com.san.calculator.server.publisher;

import com.san.calculator.server.rest.impl.TeamManagement;

import javax.xml.ws.Endpoint;

public class Publisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/team", new TeamManagement());
    }
}
