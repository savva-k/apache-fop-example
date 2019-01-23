package com.imsavva;

import com.imsavva.beans.TechnicalSpecification;
import com.imsavva.pdf.TechnicalSpecificationGenerator;

/**
 * @author Savva Kodeikin
 */
public class ApacheFopTestApp {
    public static void main(String[] args) {
        TechnicalSpecificationGenerator generator = new TechnicalSpecificationGenerator();
        TechnicalSpecification specification = TechnicalSpecification.getTestData();
        generator.generatePdf(specification, "output.pdf");
    }
}
