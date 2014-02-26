package com.san.calculator.client;

import com.san.calculator.server.rest.HelloInterface;
import com.san.calculator.server.entities.Book;
import org.glassfish.jersey.client.proxy.WebResourceFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class BookClient {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/Calculator/rest/");
        HelloInterface bookif = WebResourceFactory.newResource(HelloInterface.class, target);

        Book book = new Book("name", "desc");
        Book result = bookif.getBook(book);

        System.out.println(result.getName() + " : " + result.getDesc());
        System.out.println(bookif.sayHtmlHello());
    }
}
