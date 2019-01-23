package com.imsavva.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Savva Kodeikin
 */
public class PropertiesSection {
    private String sectionName;
    private Map<String, List<String>> values = new LinkedHashMap<>();

    public PropertiesSection() {}

    public PropertiesSection(String sectionName) {
        this.sectionName = sectionName;
    }

    public static List<PropertiesSection> getTestData() {
        List<PropertiesSection> sections = new ArrayList<>();

        PropertiesSection section1 = new PropertiesSection("Suitable drones");
        PropertiesSection section2 = new PropertiesSection("Batteries");
        PropertiesSection section3 = new PropertiesSection("Other characteristics");
        PropertiesSection section4 = new PropertiesSection("Yet another section");

        section1.addMultipleValue("DJI", "Mavic 2 Pro", "Mavic 2 Zoom", "Mavic Pro");
        section1.addValue("Force1", "U45W Blue Jay");

        section2.addValue("Battery type", "AAA");
        section2.addValue("Batteries count", "4");
        section2.addValue("Can use rechargeable batteries", "Yes");
        section2.addValue("Some other prop", "Some other value");

        section3.addValue("Prop 1", "Value 1");
        section3.addValue("Prop 2", "Value 2");
        section3.addMultipleValue("Multiple props", "Value A", "Value B", "Value C");

        section4.addValue("Prop 1", "This section is going to start on the first page");
        section4.addValue("Prop 2", "And continue on the second page");
        section4.addValue("Prop 3", "To demonstrate that we can duplicate a table's header");
        section4.addValue("Prop 4", "On the next page");
        section4.addValue("Prop 5", "(And here I noticed that the sections are shuffled when displayed)");
        section4.addValue("Prop 6", "(This can be fixed by using LinkedHashMap instead of HashMap)");
        section4.addValue("Prop 7", "(But I won't do that for now)");
        section4.addValue("Prop 8", "(P.s. eventually I decided to fix it :))");

        sections.add(section1);
        sections.add(section2);
        sections.add(section3);
        sections.add(section4);

        return sections;
    }

    public void addValue(String key, String value) {
        List<String> values = this.values.get(key);

        if (values == null) {
            values = new ArrayList<>();
            this.values.put(key, values);
        }

        values.add(value);
    }

    public void addMultipleValue(String key, String... values) {
        List<String> valuesList = this.values.get(key);

        if (valuesList == null) {
            valuesList = new ArrayList<>();
            this.values.put(key, valuesList);
        }

        valuesList.addAll(Arrays.asList(values));
    }

    public Map<String, List<String>> getValues() {
        return values;
    }

    public void setValues(Map<String, List<String>> values) {
        this.values = values;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
