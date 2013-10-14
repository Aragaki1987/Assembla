package com.san.xmlprocessor;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DefaultHandler2;

/**
 * Example of how to use the SAX parser to parse an XML document.
 * Based on the example from the JAXP tutorial.
 *
 * @author Ivan A Krizsan
 */
public class SAXExample extends DefaultHandler2 {
    /* Constant(s): */
    private final static String KOMPIS_XML_FILE_NAME = "src/com/san/xmlprocessor/kompisar.xml";
    private final static String SAX_LEXICAL_HANDLER_PROPERTY =
            "http://xml.org/sax/properties/lexical-handler";
    private final static String JAXP_SCHEMA_LANGUAGE =
            "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private final static String W3C_XML_SCHEMA =
            "http://www.w3.org/2001/XMLSchema";
    /* Instance variable(s): */
    private HashMap<String, Integer> mTags;

    /**
     * @param args
     */
    public static void main(String[] args) {
        SAXExample theExample = new SAXExample();
        theExample.runExample();
    }

    /**
     * Runs the example program.
     */
    private void runExample() {
        String theFileURL;
        theFileURL = convertToFileURL(KOMPIS_XML_FILE_NAME);
        try {
            /* Retrieve a SAX parser factory and create a SAX parser. */
            SAXParserFactory theSAXParserFactory = SAXParserFactory.newInstance();
            theSAXParserFactory.setNamespaceAware(true);
            theSAXParserFactory.setValidating(true);
            SAXParser theSAXParser = theSAXParserFactory.newSAXParser();
            /*
            * This is required if we want the SAX parser to validate the
            * XML file. In our case, the corresponding XML schema is
            * specified in the XML file.
            */
            theSAXParser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            /* Get and configure the object that is to parse the XML file. */
            XMLReader theXMLReader = theSAXParser.getXMLReader();
            theXMLReader.setContentHandler(this);
            theXMLReader.setErrorHandler(new SAXErrorHandler());
/*
* This is needed in order to receive notifications to a
* lexical handler.
*/
            theXMLReader.setProperty(SAX_LEXICAL_HANDLER_PROPERTY, this);
/* Finally we are ready to parse the XML file! */
            theXMLReader.parse(theFileURL);
        } catch (Exception theException) {
            theException.printStackTrace();
        }
    }

    /**
     * Converts supplied filename to an URL pointing to the file.
     *
     * @param inFileName File name.
     * @return URL pointing to file with supplied name.
     */
    private String convertToFileURL(final String inFileName) {
        String path = new File(inFileName).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }

    /**
     * Receives notification of start of document from the SAX parser.
     * See the org.xml.sax.ContentHandler interface for more information.
     *
     * @throws SAXException If error occurs.
     */
    @Override
    public void startDocument() throws SAXException {
        mTags = new HashMap<String, Integer>();
        System.out.println("*** ContentHandler.startDocument()");
    }

    /**
     * Receives notification of end of document from the SAX parser.
     * See the org.xml.sax.ContentHandler interface for more information.
     *
     * @throws SAXException If error occurs.
     */
    @Override
    public void endDocument() throws SAXException {
        Iterator<String> theTagsIter = mTags.keySet().iterator();
        while (theTagsIter.hasNext()) {
            String theTag = theTagsIter.next();
            int theCount = (mTags.get(theTag)).intValue();
            System.out.println("Name \"" + theTag + "\" occurs " + theCount
                    + " times");
        }
        System.out.println("*** ContentHandler.endDocument()");
    }

    /**
     * Receives notification of start of an XML element from the SAX parser.
     * See the org.xml.sax.ContentHandler interface for more information.
     * *
     *
     * @param inNamespaceURI Namespace URI of element, or empty string if
     *                       no URI or if namespace processing is not being done.
     * @param inLocalName    Local name of element, or empty string if
     *                       namespace processing not done.
     * @param inQName        Qualified name of element, or empty string if
     *                       qualified name not available.
     * @param inAttributes   Attributes attached to element, or empty
     *                       object if no attributes.
     * @throws SAXException If error occurs.
     */
    public void startElement(final String inNamespaceURI,
                             final String inLocalName, final String inQName,
                             final Attributes inAttributes) throws SAXException {
        String theKey;
        if (!inQName.equals("")) {
            theKey = inQName;
        } else {
            theKey = inLocalName;
        }
        Integer theValue = mTags.get(theKey);
        if (theValue == null) {
            mTags.put(theKey, new Integer(1));
        } else {
            int theTagCount = theValue.intValue();
            theTagCount++;
            mTags.put(theKey, new Integer(theTagCount));
        }
        System.out.println("*** ContentHandler.startElement(" + inNamespaceURI
                + ", " + inLocalName + ", " + inQName + ")");
    }

    /**
     * Receives notifications of a comment.
     * * See the org.xml.sax.ext.LexicalHandler interface for more information.
     *
     * @param inCharacters  Characters in the comment.
     * @param inStartOffset Starting offset of comment in above array.
     * @param inCharCount   Number of characters of comment in above array.
     */
    @Override
    public void comment(final char[] inCharacters, final int inStartOffset,
                        final int inCharCount) {
        System.out.println("*** LexicalHandler.comment() : "
                + new String(inCharacters) + ", start=" + inStartOffset
                + ", count=" + inCharCount);
    }
}

