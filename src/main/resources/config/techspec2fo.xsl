<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
    <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>
    <xsl:param name="versionParam" select="'1.0'"/>

    <xsl:template match="technicalSpecification">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="1.5cm" margin-bottom="1.5cm" margin-left="1.5cm" margin-right="1.5cm">
                    <fo:region-body margin-top="1.5cm" margin-bottom="2.5cm"/>
                    <fo:region-before display-align="before"/>
                    <fo:region-after extent="80mm" display-align="after"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="simpleA4" id="pdfPage">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block border-bottom="0.5pt silver solid">This header will appear on each page</fo:block>
                </fo:static-content>
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block>
                        <fo:block border-bottom="0.5pt silver solid" font-size="6pt" space-after="3mm" padding-bottom="3mm">
                                <fo:inline font-weight="bold">Expample of inline formatting:</fo:inline>
                                This footer will appear on each page
                        </fo:block>
                        <fo:block font-size="6pt">
                            Page <fo:page-number/> of <fo:page-number-citation-last ref-id="pdfPage"/>
                        </fo:block>
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <!-- Header -->
                    <fo:table border="0" space-after="10mm">
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block space-after="5mm">
                                        <fo:external-graphic src="url('{imageUrl}')" content-width="80mm"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block font-size="16pt" font-weight="bold" space-after="3mm">
                                        <xsl:value-of select="name"/>
                                    </fo:block>
                                    <fo:block font-size="10pt" font-weight="bold" space-after="3mm">
                                        <fo:inline font-weight="bold" padding="2mm" padding-bottom="1mm" background-color="#FFCC99">Best value</fo:inline>
                                    </fo:block>
                                    <fo:block font-size="10pt" space-after="5mm">
                                        <xsl:value-of select="productId"/>
                                    </fo:block>
                                    <fo:block font-size="12pt" space-after="5mm" linefeed-treatment="preserve">
                                        <xsl:value-of select="description"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <!-- Technical specifications label -->
                    <fo:block font-size="16pt" font-weight="bold" space-after="7mm">
                        Technical Specification
                    </fo:block>

                    <!-- Technical specifications sections -->
                    <xsl:for-each select="propertySection">
                        <fo:table border="0" space-after="10mm">
                            <fo:table-header>
                                <fo:table-row>
                                    <fo:table-cell padding="2pt" number-columns-spanned="2">
                                        <fo:block font-size="14pt" space-after="3mm">
                                            <xsl:value-of select="name"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-header>
                            <fo:table-body>
                                <xsl:for-each select="sectionEntry">
                                    <fo:table-row border-bottom="1pt solid #333">
                                        <fo:table-cell padding="2pt">
                                            <fo:block font-size="10pt"><xsl:value-of select="paramName"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt">
                                            <fo:block>
                                                <xsl:for-each select="paramValues/paramValue">
                                                    <fo:block font-size="10pt"><xsl:value-of select="text()"/></fo:block>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:for-each>
                            </fo:table-body>
                        </fo:table>
                    </xsl:for-each>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
