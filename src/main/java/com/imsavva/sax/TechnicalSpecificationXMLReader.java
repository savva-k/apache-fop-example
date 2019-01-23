package com.imsavva.sax;

import com.imsavva.beans.PropertiesSection;
import com.imsavva.beans.TechnicalSpecification;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Savva Kodeikin
 */
public class TechnicalSpecificationXMLReader extends AbstractObjectReader {

    @Override
    public void parse(InputSource input) throws IOException, SAXException {
        if (input instanceof TechnicalSpecificationInputSource) {
            TechnicalSpecificationInputSource specification = (TechnicalSpecificationInputSource) input;
            parse(specification.getTechnicalSpecification());
        } else {
            throw new SAXException("Unsupported InputSource specified.");
        }
    }

    public void parse(TechnicalSpecification technicalSpecification) throws SAXException {
        if (technicalSpecification == null) {
            throw new NullPointerException("Parameter technicalSpecification must not be null");
        }
        if (handler == null) {
            throw new IllegalStateException("ContentHandler not set");
        }

        handler.startDocument();
        generateFor(technicalSpecification);
        handler.endDocument();
    }


    /**
     * Generates SAX events for a TechnicalSpecification object.
     * @param technicalSpecification TechnicalSpecification object to use
     * @throws SAXException In case of a problem during SAX event generation
     */
    protected void generateFor(TechnicalSpecification technicalSpecification) throws SAXException {
        if (technicalSpecification == null) {
            throw new NullPointerException("Parameter technicalSpecification must not be null");
        }

        handler.startElement("technicalSpecification");
        handler.element("productId", technicalSpecification.getProductId());
        handler.element("name", technicalSpecification.getProductName());
        handler.element("description", technicalSpecification.getProductDescription());
        handler.element("imageUrl", technicalSpecification.getImageUrl());
        for (PropertiesSection section : technicalSpecification.getSections()) {
            generateFor(section);
        }
        handler.endElement("technicalSpecification");
    }

    /**
     * Generates SAX events for a PropertiesSection object.
     * @param propertiesSection PropertiesSection object to use
     * @throws SAXException In case of a problem during SAX event generation
     */
    protected void generateFor(PropertiesSection propertiesSection) throws SAXException {
        if (propertiesSection == null) {
            throw new NullPointerException("Parameter propertiesSection must not be null");
        }

        handler.startElement("propertySection");
        handler.element("name", propertiesSection.getSectionName());
        generateFor(propertiesSection.getValues());
        handler.endElement("propertySection");
    }

    /**
     * Generates SAX events for a Map<String, List<String>> object.
     * @param sectionEntries Map<String, List<String>> object to use
     * @throws SAXException In case of a problem during SAX event generation
     */
    protected void generateFor(Map<String, List<String>> sectionEntries) throws SAXException {
        if (sectionEntries == null) {
            throw new NullPointerException("Parameter sectionEntries must not be null");
        }

        for (Map.Entry<String, List<String>> entry : sectionEntries.entrySet()) {
            handler.startElement("sectionEntry");
            handler.element("paramName", entry.getKey());
            generateFor(entry.getValue());
            handler.endElement("sectionEntry");
        }
    }

    /**
     * Generates SAX events for a List<String> object.
     * @param paramValues Map<String, List<String>> object to use
     * @throws SAXException In case of a problem during SAX event generation
     */
    protected void generateFor(List<String> paramValues) throws SAXException {
        if (paramValues == null) {
            throw new NullPointerException("Parameter paramValues must not be null");
        }

        handler.startElement("paramValues");
        for (String value : paramValues) {
            handler.element("paramValue", value);
        }
        handler.endElement("paramValues");
    }

}
