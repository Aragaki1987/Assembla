package com.san.calculator.server.impl;


import com.san.calculator.server.HelloInterface;
import com.san.calculator.server.entities.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import java.util.ArrayList;

@Path("/hello")
public class HelloRestful {
    // This method is called if TEXT_PLAIN is request
    private static ArrayList<Book> books = new ArrayList<Book>();

    @GET
    @Path("/text")
    @Produces("text/plain")
    public String sayPlainTextHello() {
        return "Hello Jersey";
    }

    // This method is called if XML is request
    @GET
    @Path("xml")
    @Produces("text/xml")
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
    }

    // This method is called if HTML is request
    @GET
    @Path("/html")
    @Produces("text/html")
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_XML)
    public Book getBook(Book book) {
        book.setDesc("Modified");
        books.add(book);
        return book;
    }

    @GET
    @Path("size")
    @Consumes(MediaType.APPLICATION_XML)
    public Integer getBooksSize() {
        return books.size();
    }
}
