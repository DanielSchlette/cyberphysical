/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.cpe.sql;

import cps.extended.concept.dao.DbManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Retrieves mappings between CPEs und CVEs from CVE file
 *
 * @author tarnschaf
 */
public class CveCpeHandler extends DefaultHandler implements Serializable {

    @Inject
    DbManager dbManager;

    private static final String VULN = "http://scap.nist.gov/schema/vulnerability/0.4";

    private boolean product = false;

    private StringBuilder data = null;

    // ids to matching string
    Map<String, Long> cves = new HashMap();
    Map<String, Long> cpes = new HashMap();
    // Set of CPE keys
    Set<String> cpeKeys = cpes.keySet();

    private Long cveId;

    private long counter = 0;

    Map<Long, List<Long>> cveCpeMapping = new HashMap();

    List<Long> cpesList;

    public CveCpeHandler(HashMap<String, Long> matchToCpeId, HashMap<String, Long> matchToCveId) {
        this.cpes = matchToCpeId;
        this.cves = matchToCveId;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("entry")) {

            if (cpesList == null) {
                cpesList = new ArrayList();
            }

            cveId = cves.get(attributes.getValue("id"));

            counter++;

        } else if (uri.equalsIgnoreCase(VULN) && localName.equalsIgnoreCase("product")) {
            product = true;
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName,
            String qName) throws SAXException {
        // check vulnerable software entries in CVE Database with CPE 2.2 identifiers
        if (product) {
            String cpeStr = data.toString();
            if (cpeStr.contains("::")) {
                String[] splits = cpeStr.split("::");
                // always two strings
                String first = splits[0];
                String last = splits[1];
                for (String cpeKey : cpeKeys) {
                    if (cpeKey.startsWith(first) && cpeKey.endsWith(last)) {
                        Long nestedId = cpes.get(cpeKey);
                        // maps Cve to Cpe
                        cpesList.add(nestedId);
                    }
                }
            } else {
                Long id = cpes.get(cpeStr);
                cpesList.add(id);
            }
            product = false;
        }

        if (qName.equalsIgnoreCase("entry")) {
            cveCpeMapping.put(cveId, cpesList);
            cpesList = null;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }

    public Map<Long, List<Long>> getMap() {
        return cveCpeMapping;
    }
}
