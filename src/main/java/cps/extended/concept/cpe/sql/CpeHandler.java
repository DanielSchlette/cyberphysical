/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.cpe.sql;

import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.asset.Cpe;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Retrieves CPE date from XML file
 * 
 * @author tarnschaf
 */
public class CpeHandler extends org.xml.sax.helpers.DefaultHandler {

    @Inject
    DbManager dbm;

    private List<Cpe> cyberCpes = null;

    private Cpe cyberCpe = null;

    private StringBuilder data = null;

    // getter method for employee list
    public List<Cpe> getCpeList() {
        return cyberCpes;
    }

    boolean title = false;
    boolean cpe23 = false;

    private String cyberCpe23;

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("cpe-item")) {
            String cyberCpe22 = attributes.getValue("name");

            // initialize Cpe object and set CPE 2.2 input
            cyberCpe = new Cpe();
            cyberCpe.set22Uri(cyberCpe22);

            if (cyberCpes == null) {
                cyberCpes = new ArrayList<>();
            }

        } else if (qName.equalsIgnoreCase("title")) {
            title = true;
        } else if (qName.equalsIgnoreCase("cpe-23:cpe23-item")) {
            cyberCpe23 = attributes.getValue("name");
            cpe23 = true;
        }
        // create the data container
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName,
            String qName) throws SAXException {

        if (title) {
            cyberCpe.setTitle(data.toString().replace("'", ""));
            title = false;
        }

        if (cpe23) {
            cyberCpe.set23Uri(cyberCpe23);
            cpe23 = false;
        }
        if (qName.equalsIgnoreCase("cpe-item")) {
            // add CPE object to list
            cyberCpes.add(cyberCpe);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}
