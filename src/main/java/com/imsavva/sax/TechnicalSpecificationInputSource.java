package com.imsavva.sax;

import com.imsavva.beans.TechnicalSpecification;
import org.xml.sax.InputSource;

/**
 * @author Savva Kodeikin
 */
public class TechnicalSpecificationInputSource extends InputSource {

    private TechnicalSpecification technicalSpecification;

    public TechnicalSpecificationInputSource(TechnicalSpecification technicalSpecification) {
        this.technicalSpecification = technicalSpecification;
    }

    public TechnicalSpecification getTechnicalSpecification() {
        return technicalSpecification;
    }

    public void setTechnicalSpecification(TechnicalSpecification technicalSpecification) {
        this.technicalSpecification = technicalSpecification;
    }
}
