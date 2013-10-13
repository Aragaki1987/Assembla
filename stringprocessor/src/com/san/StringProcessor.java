package com.san;

import com.san.beans.ObjectFactory;
import com.san.beans.ReverseStringReq;
import com.san.beans.ReverseStringResp;

import javax.jws.soap.SOAPBinding;
import javax.xml.bind.*;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Source;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

/**
 * This class implements a web service that processes strings.
 * The endpoint is implemented as a provider that receives and processes
 * message payloads (SOAP messages in this case), as opposed to web service
 * endpoint using the @WebService interface, which provides a service
 * endpoint interface and which receives already unmarshalled parameters.
 */
@WebServiceProvider(
        wsdlLocation = "StringProcessorService.wsdl",
        portName = "StringProcessorServicePort",
        serviceName = "StringProcessorService",
        targetNamespace = "http://www.san.com/stringprocessor")
@ServiceMode(value = Service.Mode.PAYLOAD)
@SOAPBinding(
        parameterStyle = SOAPBinding.ParameterStyle.WRAPPED,
        style = SOAPBinding.Style.DOCUMENT,
        use = SOAPBinding.Use.LITERAL)
public class StringProcessor implements Provider<Source> {
    /* (non-Javadoc)
    * @see javax.xml.ws.Provider#invoke(java.lang.Object)
    */
    public Source invoke(final Source inRequestMessage) {
        Source theResponseSource = null;
        try {
    /* Prepare JAXB for marshaling and unmarshaling. */
            JAXBContext theJaxbContext =
                    JAXBContext.newInstance("com.san.beans");
            Unmarshaller theUnmarshaller = theJaxbContext.createUnmarshaller();
            Marshaller theMarshaller = theJaxbContext.createMarshaller();
    /* Use JAXB to unmarshal the message payload into a request-bean. */
            JAXBElement<ReverseStringReq> theReqElem =
                    (JAXBElement<ReverseStringReq>) theUnmarshaller
                            .unmarshal(inRequestMessage);
            ReverseStringReq theReq = theReqElem.getValue();
            System.out.println("Got a request to reverse the string: " +
                    theReq.getInString());
    /*
    * Do the processing supplied by the operation - that is
    * reverse the string.
    */
            String theResultString =
                    (new StringBuffer(theReq.getInString())).reverse().toString();
            System.out.println("Processed the result: " + theResultString);
    /* Create the response message payload and set the result. */
            ObjectFactory theObjFactory = new ObjectFactory();
            ReverseStringResp theResponse =
                    theObjFactory.createReverseStringResp();
            theResponse.setReturn(theResultString);
    /*
    * Create a JAXB source that will marshal the supplied
    * JAXB bean to XML when the response message is assembled.
    */
            theResponseSource = new JAXBSource(theJaxbContext, theResponse);
        } catch (JAXBException theException) {
            theException.printStackTrace();
        }
        return theResponseSource;
    }
}
