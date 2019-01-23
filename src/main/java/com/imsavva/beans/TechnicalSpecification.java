package com.imsavva.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Savva Kodeikin
 */
public class TechnicalSpecification {
    private String productId;
    private String productName;
    private String productDescription;
    private String imageUrl;
    private List<PropertiesSection> sections = new ArrayList<>();

    public TechnicalSpecification() {}

    public TechnicalSpecification(String productId, String productName, String productDescription, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.imageUrl = imageUrl;
    }

    public static TechnicalSpecification getTestData() {
        String id = "PRD0000001";
        String name = "A drone controller";
        String description = "A long long description of the product\n" +
                "\n" +
                "It has multiple lines";
        String image = "https://img.grouponcdn.com/deal/4NLn8JeRM1nFXdTQfMtiTYubM9it/4N-1800x1080/v1/c700x420.jpg";
        TechnicalSpecification specification = new TechnicalSpecification(id, name, description, image);
        specification.setSections(PropertiesSection.getTestData());
        return specification;
    }

    public void addSection(PropertiesSection section) {
        this.sections.add(section);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<PropertiesSection> getSections() {
        return sections;
    }

    public void setSections(List<PropertiesSection> sections) {
        this.sections = sections;
    }
}
