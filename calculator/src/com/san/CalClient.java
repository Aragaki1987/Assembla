package com.san;

import com.san.beans.AddNumbers;
import com.san.beans.AddNumbersResponse;
import com.san.beans.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class CalClient {

    private final static String WSDL_LOCATION =
            "http://localhost:8080/JAX-WS_Server_wsgen/CalculatorService?wsdl";
    private final static String SERVICE_NAMESPACE =
            "http://www.san.com/calculator";
    private final static String SERVICE_NAME = "CalculatorService";
    private final static String SERVICE_PORT_NAME = "CalculatorPort";
    private void callSyncReqRespService()
    {
        try
        {
            Dispatch<Object> theDispatch = prepareCalculatorDispatch();
            JAXBElement<AddNumbers> theReqElem = createRequestPayload();
/* Invoke the service operation using the Dispatch object. */
            JAXBElement<AddNumbersResponse> theResponse =
                    (JAXBElement<AddNumbersResponse>)theDispatch.invoke(theReqElem);
/* Retrieve the result from the response message. */
            int theSum = theResponse.getValue().getReturn();
            System.out.println("The sum calculated by the web service: "
                    + theSum);
        } catch (Exception theException)
        {
            theException.printStackTrace();
        }
    }
    private JAXBElement<AddNumbers> createRequestPayload()
    {
        ObjectFactory theJaxbObjFactory = new ObjectFactory();
/*
* Create the JAXB bean holding the request payload and
* set parameters of the service operation invocation.
*/
        AddNumbers theAddRequestBean = theJaxbObjFactory.createAddNumbers();
        theAddRequestBean.setArg0(4);
        theAddRequestBean.setArg1(5);
/*
* Retrieve the JAXB element of the request operation.
* * This element is responsible for producing the XML of the
* message payload when the message later is to be transmitted.
*/
        JAXBElement<AddNumbers> theReqElem =
                theJaxbObjFactory.createAddNumbers(theAddRequestBean);
        return theReqElem;
    }
    private Dispatch<Object> prepareCalculatorDispatch()
            throws MalformedURLException, JAXBException
    {
        QName theName =
                new QName(SERVICE_NAMESPACE, SERVICE_PORT_NAME);
        URL theWsdlUrl = new URL(WSDL_LOCATION);
        QName theServiceName = new QName(SERVICE_NAMESPACE, SERVICE_NAME);
        Service theService = Service.create(theWsdlUrl, theServiceName);
        JAXBContext theJaxbContext =
                JAXBContext.newInstance("com.ivan.calculator");
/*
* Note the constant Service.Mode.PAYLOAD parameter in the call
* to the createDispatch method. This is where the client decides whether
* it wants to work with entire messages (Service.Mode.MESSAGE) or
* message payloads only (Service.Mode.PAYLOAD).
*/
        Dispatch<Object> theDispatch =
                theService.createDispatch(theName, theJaxbContext,
                        Service.Mode.PAYLOAD);
        return theDispatch;
    }

}
