package com.san;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.WebServiceContext;

/**
 * Calculator service implementation class.
 */
@WebService(
        name = "Calculator",
        serviceName = "CalculatorService",
        targetNamespace = "http://www.san.com/calculator",
        wsdlLocation="CalculatorService.wsdl")
@SOAPBinding(
        parameterStyle=SOAPBinding.ParameterStyle.WRAPPED,
        style=SOAPBinding.Style.DOCUMENT,
        use=SOAPBinding.Use.LITERAL)
public class Calculator
{
    /* Instance variable(s): */
    @Resource private WebServiceContext mWSContext;
    /**
     * Initializes the web service.
     */
    @PostConstruct
    @WebMethod(exclude = true)
    public void init()
    {
        System.out.println("Web service initialized, service context: " + mWSContext);
    }
    /**
     * Adds the supplied numbers.
     *
     * @param inNumber1 First number to add.
     * @param inNumber2 Second number to add.
     * @return Sum of the two numbers.
     */
    @WebMethod(operationName = "addNumbers", action = "urn:Add")
    @ResponseWrapper(
            className = "com.san.stub.AddResponse",
            localName = "addNumbersResponse",
            targetNamespace = "http://www.san.com/calculator")
    @RequestWrapper(
            className="com.san.stub.Add",
            localName="addNumbers",
            targetNamespace="http://www.san.com/calculator")
    public int add(final int inNumber1, final int inNumber2)
    {
        System.out.println("Adding " + inNumber1 + " and " + inNumber2);
        return inNumber1 + inNumber2;
    }
}
