/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.cpe.sql;

import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.vulnerability.Cve;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Retrieves CVE date from XML file
 * 
 * @author tarnschaf
 */
@SessionScoped
public class CveHandler extends org.xml.sax.helpers.DefaultHandler implements Serializable {

    @Inject
    DbManager dbManager;

    private static final String VULN = "http://scap.nist.gov/schema/vulnerability/0.4";

    private static final String CPE_LANG = "http://scap.nist.gov/schema/vulnerability/0.4";

    private List<Cve> cves = null;

    private Cve cve = null;

    private boolean description = false;
    private boolean date = false;
    private boolean reference = false;

    private List<String> urls;

    private StringBuilder data = null;

    public CveHandler() {
    }

    public List<Cve> getCveList() {
        return cves;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("entry")) {
            String cveId = attributes.getValue("id");

            // initialize Cve object
            cve = new Cve();
            cve.setCveId(cveId);
            cve.setSource("Cyber");
            if (cves == null) {
                cves = new ArrayList<>();
            }
        } else if (uri.equalsIgnoreCase(VULN) && localName.equalsIgnoreCase("summary")) {
            description = true;
        } else if (uri.equalsIgnoreCase(VULN) && localName.equalsIgnoreCase("published-datetime")) {
            date = true;
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName,
            String qName) throws SAXException {
        if (description) {
            cve.setDescription(data.toString().replace("'", ""));
            description = false;
        } else if (date) {
            cve.setDate(data.toString());
            date = false;
        }
        if (qName.equalsIgnoreCase("entry")) {
            cves.add(cve);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}
