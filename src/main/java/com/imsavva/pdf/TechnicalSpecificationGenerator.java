package com.imsavva.pdf;

import com.imsavva.beans.TechnicalSpecification;
import com.imsavva.sax.TechnicalSpecificationInputSource;
import com.imsavva.sax.TechnicalSpecificationXMLReader;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FopFactoryBuilder;
import org.apache.fop.apps.MimeConstants;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Savva Kodeikin
 */
public class TechnicalSpecificationGenerator {

    private static final String FOP_CONFIG_FILE = "/config/fop-sample-config.xml";
    private static final String TECHSPEC_XSL_FILE = "/config/techspec2fo.xsl";
    private static final String FOP_BASE_DIR = TechnicalSpecificationGenerator.class.getResource("/").getFile();

    private FopFactory fopFactory;

    public TechnicalSpecificationGenerator() {
        try {
            DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
            System.out.println(FOP_BASE_DIR);
            Configuration config = cfgBuilder.build(getClass().getResourceAsStream(FOP_CONFIG_FILE));
            FopFactoryBuilder factoryBuilder = new FopFactoryBuilder(new File(FOP_BASE_DIR).toURI()).setConfiguration(config);
            fopFactory = factoryBuilder.build();
        } catch (Exception e) {
            e.printStackTrace(); // Always log errors in your code :)
        }
    }

    public void generatePdf(TechnicalSpecification spec, String outputFilePath) {
        StreamSource techSpec2FoStreamSource = new StreamSource(getClass().getResourceAsStream(TECHSPEC_XSL_FILE));
        File pdfFile = new File(outputFilePath);

        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, fopFactory.newFOUserAgent(), new FileOutputStream(pdfFile));
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(techSpec2FoStreamSource);

            Source source = new SAXSource(new TechnicalSpecificationXMLReader(), new TechnicalSpecificationInputSource(spec));
            Result result = new SAXResult(fop.getDefaultHandler());
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
