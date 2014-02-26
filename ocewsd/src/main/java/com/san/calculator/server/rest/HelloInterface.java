package com.san.calculator.server.rest;

import com.san.calculator.server.entities.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public interface HelloInterface {

    @GET
    @Path("/text")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello();

    @GET
    @Path("/xml")
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello();

    @GET
    @Path("/html")
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello();

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_XML)
    public Book getBook(Book book);

    @GET
    @Path("size")
    @Consumes(MediaType.APPLICATION_XML)
    public Integer getBooksSize();
}
