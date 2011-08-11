package edu.ntu.eee.csn.ism.egene.util;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ServletResponse {

	private static Logger LOGGER = Logger.getLogger(ServletResponse.class);

	public static String success(String requestName) {

		String result = "<egene-webresponse><request-name>"
				+ requestName
				+ "</request-name><request-status>OK</request-status></egene-webresponse>";
		try {
			StringWriter sw = new StringWriter();
			XMLOutputFactory xof = XMLOutputFactory.newInstance();
			XMLStreamWriter xtw = null;
			xtw = xof.createXMLStreamWriter(sw);

			xtw.writeStartElement("egene-webresponse");

			xtw.writeStartElement("request-name");
			xtw.writeCharacters(requestName);
			xtw.writeEndElement();

			xtw.writeStartElement("request-status");
			xtw.writeCharacters("OK");
			xtw.writeEndElement();

			xtw.writeEndElement();
			xtw.writeEndDocument();
			xtw.flush();
			xtw.close();

			result = sw.toString();
		} catch (XMLStreamException e) {
			if (LOGGER.isEnabledFor(Level.WARN))
				LOGGER.warn(e.getMessage(), e);
		}

		return result;
	}

	public static String fail(String requestName) {

		String result = "<egene-webresponse><request-name>"
				+ requestName
				+ "</request-name><request-status>KO</request-status></egene-webresponse>";
		try {
			StringWriter sw = new StringWriter();
			XMLOutputFactory xof = XMLOutputFactory.newInstance();
			XMLStreamWriter xtw = null;
			xtw = xof.createXMLStreamWriter(sw);

			xtw.writeStartElement("egene-webresponse");

			xtw.writeStartElement("request-name");
			xtw.writeCharacters(requestName);
			xtw.writeEndElement();

			xtw.writeStartElement("request-status");
			xtw.writeCharacters("KO");
			xtw.writeEndElement();

			xtw.writeEndElement();
			xtw.writeEndDocument();
			xtw.flush();
			xtw.close();

			result = sw.toString();
		} catch (XMLStreamException e) {
			if (LOGGER.isEnabledFor(Level.WARN))
				LOGGER.warn(e.getMessage(), e);
		}

		return result;
	}

}
