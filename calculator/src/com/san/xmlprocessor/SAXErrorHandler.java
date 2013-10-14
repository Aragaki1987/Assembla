package com.san.xmlprocessor;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
/**
 * This class implements an error handler that can be used both with
 * SAX and DOM.
 *
 * @author Ivan A Krizsan
 */
public class SAXErrorHandler implements ErrorHandler
{
    /* Constant(s): */
/* Instance variable(s): */
/* (non-Javadoc)
* @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
*/
    public void error(final SAXParseException inException) throws SAXException
    {
        int theLineNo = inException.getLineNumber();
        System.out.println("*** ErrorHandler.error() : "
                + inException.getLocalizedMessage() + " at line " + theLineNo);
    }
    /* (non-Javadoc)
    * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
    */
    public void fatalError(final SAXParseException inException) throws SAXException
    {
        int theLineNo = inException.getLineNumber();
        System.out.println("*** ErrorHandler.fatalError() : "
                + inException.getLocalizedMessage() + " at line " + theLineNo);
    }
    /* (non-Javadoc)
    * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
    */
    public void warning(final SAXParseException inException) throws SAXException
    {
        int theLineNo = inException.getLineNumber();
        System.out.println("*** ErrorHandler.warning() : "
                + inException.getLocalizedMessage() + " at line " + theLineNo);
    }
}