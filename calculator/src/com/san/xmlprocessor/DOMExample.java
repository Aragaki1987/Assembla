package com.san.xmlprocessor;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Example of how to use the DOM parser to read and modify an XML document.
 * Based on the example from the JAXP tutorial.<br/>
 * Note that the error handler interface is the same as in the SAX
 * example, but in this example we implement it directly. The exact same
 * error handling can be used for both SAX and DOM.
 *
 * @author Ivan A Krizsan
 */
public class DOMExample {
    /* Constant(s): */
    private final static String KOMPIS_XML_FILE_NAME = "src/com/san/xmlprocessor/kompisar.xml";
    private final static String JAXP_SCHEMA_LANGUAGE =
            "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private final static String W3C_XML_SCHEMA =
            "http://www.w3.org/2001/XMLSchema";
    /* Instance variable(s): */
    private int mIndent = 0;

    /**
     * Main entry point of program.
     *
     * @param inArguments Command-line arguments. Not used.
     */
    public static void main(String[] inArguments) {
        DOMExample theExample = new DOMExample();
        try {
            theExample.runExample();
        } catch (Exception theException) {
            theException.printStackTrace();
        }
    }

    /**
     * Runs the example program.
     *
     * @throws Exception If error occurs.
     */
    private void runExample() throws Exception {
        Document theKompisDocument = readXMLDocument(KOMPIS_XML_FILE_NAME);
/* Output some information about the document. */
        System.out.println("The document : " + theKompisDocument);
        System.out.println("XML version : "
                + theKompisDocument.getXmlVersion());
        System.out.println("XML encoding : "
                + theKompisDocument.getXmlEncoding());
/* Output the in-memory representation of the document. */
        doTraverseNode(theKompisDocument);
    }

    /**
     * Reads and validates the XML document with the supplied name.
     *
     * @param inXMLFileName Name of XML file to read.
     * @return DOM document containing the XML document representation.
     * @throws Exception If error occurs reading XML document.
     */
    public Document readXMLDocument(final String inXMLFileName) throws Exception {
        DocumentBuilderFactory theDocumentBuilderFactory;
        DocumentBuilder theDocumentBuilder;
        File theXMLFile;
        Document theKompisDocument;
/*
* Retrieve the factory which creates instances of objects that
* read XML files.
*/
        theDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
/*
* Configure so that documents read are validated and so that the
* parser understands namespaces.
*/
        theDocumentBuilderFactory.setValidating(true);
        theDocumentBuilderFactory.setNamespaceAware(true);
/*
* Configure parser to ignore whitespace in element content.
* When using this feature, the parser must also be set to
* validate.
*/
        theDocumentBuilderFactory.setIgnoringElementContentWhitespace(true);
/*
* Set the schema language to XML, in order for DOM to be able to
* validate our document.
* The XML schema used to validate our document is, in this case,
* specified in the XML document.
*/
        theDocumentBuilderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE,
                W3C_XML_SCHEMA);
/*
* Retrieve a document builder that reads an XML file and
* set its error handler to this object.
*/
        theDocumentBuilder = theDocumentBuilderFactory.newDocumentBuilder();
        theDocumentBuilder.setErrorHandler(new SAXErrorHandler());
/* Read the XML file. */
        theXMLFile = new File(inXMLFileName);
        theKompisDocument = theDocumentBuilder.parse(theXMLFile);
        return theKompisDocument;
    }

    /**
     * Outputs information about the supplied DOM node and all of its children.
     *
     * @param inNode Node for which to output information.
     */
    private void doTraverseNode(final Node inNode) {
        outputNodeInformation(inNode);
        mIndent += 4;
        NodeList theChildNodes = inNode.getChildNodes();
        for (int i = 0; i < theChildNodes.getLength(); i++) {
            Node theChildNode = theChildNodes.item(i);
            doTraverseNode(theChildNode);
        }
        mIndent -= 4;
        outputIndentation();
        System.out.println("End of node=\"" + inNode.getNodeName() + "\"");
    }

    /**
     * Outputs information about the supplied DOM node.
     *
     * @param inNode Node for which to output information.
     */
    private void outputNodeInformation(final Node inNode) {
        outputIndentation();
        System.out.print("NodeName=\"" + inNode.getNodeName() + "\"");
        String theStr = inNode.getNamespaceURI();
        printNonNull(", uri=", theStr);
        theStr = inNode.getPrefix();
        printNonNull(", pre=", theStr);
        theStr = inNode.getLocalName();
        printNonNull(", local=", theStr);
        theStr = inNode.getNodeValue();
        if (theStr != null) {
            System.out.print(", nodeValue=");
            if (theStr.trim().equals("")) {
                System.out.print("[empty]");
            } else {
                System.out.print("\"" + inNode.getNodeValue() + "\"");
            }
        }
/* Output the attributes of the node, if any. */
        NamedNodeMap theAttributes = inNode.getAttributes();
        if (theAttributes != null) {
            int theAttributesCount = theAttributes.getLength();
            if (theAttributesCount > 0) {
                System.out.print(", attributes: ");
                for (int i = 0; i < theAttributesCount; i++) {
                    Node theAttribute = theAttributes.item(i);
                    System.out.print(theAttribute.getNodeName() + "=" +
                            theAttribute.getNodeValue() + ", ");
                }
            }
        }
        System.out.println();
    }

    /**
     * Prints the supplied name and value, if the value is not null.
     * The value will be printed in quotes.
     *
     * @param inName  Name to print.
     * @param inValue Value to print, or null.
     */
    private void printNonNull(final String inName, final String inValue) {
        if (inValue != null) {
            System.out.print(inName + "\"" + inValue + "\"");
        }
    }

    private void outputIndentation() {
        for (int i = 0; i < mIndent; i++) {
            System.out.print(" ");
        }
    }
}