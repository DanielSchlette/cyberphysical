/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.beans;

import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.asset.CyberPhysicalCpe;
import cps.extended.concept.entities.asset.attributes.Capability;
import cps.extended.concept.entities.asset.attributes.Communication;
import cps.extended.concept.entities.asset.attributes.Industry;
import cps.extended.concept.entities.asset.attributes.Part;
import cps.extended.concept.entities.asset.attributes.Protocol;
import cps.extended.concept.entities.vulnerability.CpsVulnerability;
import cps.extended.concept.entities.vulnerability.Cve;
import cps.extended.concept.entities.vulnerability.CveCpsVulnerabilityMapping;
import cps.extended.concept.enums.CapabilityPresettings;
import cps.extended.concept.enums.CommunicationPresettings;
import cps.extended.concept.enums.IndustryPresettings;
import cps.extended.concept.enums.PartPresettings;
import cps.extended.concept.enums.ProtocolPresettings;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Initializes the default database settings
 *
 * @author tarnschaf
 */
@SessionScoped
@Named("initDatabaseController")
public class InitDatabaseController implements Serializable {

    @Inject
    DbManager dbm;

    public InitDatabaseController() {
    }

    public void initCyberPhysicalCpeDatabase() {

        // Initializes default part settings
        if (dbm.getPartDAO().count() == 0) {
            for (PartPresettings setting : PartPresettings.values()) {
                Part part = new Part();
                part.setPart(setting.getPart());
                dbm.getPartDAO().save(part);
            }
        }

        // Initializes default communication settings
        if (dbm.getCommunicationDAO().count() == 0) {
            for (CommunicationPresettings presettings : CommunicationPresettings.values()) {
                Communication communication = new Communication();
                communication.setCommunication(presettings.getCommunication());
                dbm.getCommunicationDAO().save(communication);
            }
        }

        // Initializes default capability settings
        if (dbm.getCapabilityDAO().count() == 0) {
            for (CapabilityPresettings capabilityPresettings : CapabilityPresettings.values()) {
                Capability capability = new Capability();
                capability.setCapability(capabilityPresettings.getCapability());
                dbm.getCapabilityDAO().save(capability);
            }
        }

        // Initializes default protocol settings
        if (dbm.getProtocolDAO().count() == 0) {
            for (ProtocolPresettings protocolPresettings : ProtocolPresettings.values()) {
                Protocol protocol = new Protocol();
                protocol.setProtocol(protocolPresettings.getProtocol());
                dbm.getProtocolDAO().save(protocol);
            }
        }

        // Initializes default industry settings
        if (dbm.getIndustryDAO().count() == 0) {
            for (IndustryPresettings industryPresettings : IndustryPresettings.values()) {
                Industry industry = new Industry();
                industry.setIndustry(industryPresettings.getIndustry());
                dbm.getIndustryDAO().save(industry);
            }
        }

        generateCyberPhysicalEntry();
        generateCpsVulnerability();
    }

    private void generateCyberPhysicalEntry() {
        if (dbm.getCyberPhyiscalCpeDAO().count() == 0) {
            // Standard entry for CP-CPE (Siemens SIMATIC-S7 300)
            CyberPhysicalCpe cyberPhysicalCpe = new CyberPhysicalCpe();

            List<Capability> capabilities = new ArrayList();
            Capability capability = new Capability();
            capability.setCapability("Controlling");
            capabilities.add(capability);

            List<Protocol> protocols = new ArrayList();
            Protocol protocol1 = new Protocol();
            protocol1.setProtocol("PROFIBUS DP");
            protocols.add(protocol1);

            Protocol protocol2 = new Protocol();
            protocol2.setProtocol("PROFIBUS FMS");
            protocols.add(protocol2);

            String sqlTcpIp = "SELECT * FROM protocol WHERE protocol = '" + ProtocolPresettings.TCP_IP.getProtocol() + "'";

            EntityManager em = dbm.getCveDAO().getEntityManager();
            Query protocolQuery = em.createNativeQuery(sqlTcpIp, Protocol.class);
            Protocol protocol3 = (Protocol) protocolQuery.getResultList().get(0);
            protocols.add(protocol3);

            List<Industry> industries = new ArrayList();
            Industry industry1 = new Industry();
            industry1.setIndustry("Industrial Process Control");
            industries.add(industry1);

            Industry industry2 = new Industry();
            industry2.setIndustry("Military");
            industries.add(industry2);

            cyberPhysicalCpe.setCapabilities(capabilities);
            cyberPhysicalCpe.setProtocols(protocols);
            cyberPhysicalCpe.setIndustries(industries);

            String sqlPart = "SELECT * FROM part WHERE part = '" + PartPresettings.P.getPart() + "'";

            Query partQuery = em.createNativeQuery(sqlPart, Part.class);
            Part part = (Part) partQuery.getResultList().get(0);
            cyberPhysicalCpe.setPart(part);

            String sqlCommunication = "SELECT * FROM communication WHERE communication = '" + CommunicationPresettings.WIRED.getCommunication() + "'";

            Query communicationQuery = em.createNativeQuery(sqlCommunication, Communication.class);
            Communication communication = (Communication) communicationQuery.getResultList().get(0);
            cyberPhysicalCpe.setCommunication(communication);

            cyberPhysicalCpe.setVendor("Siemens");
            cyberPhysicalCpe.setVersion("300");
            cyberPhysicalCpe.setProduct("SIMATIC S7");
            cyberPhysicalCpe.setOther("Stuxnets targeted PLC");
            cyberPhysicalCpe.setTitle("Siemens SIMATIC S7-300");

            dbm.getCyberPhyiscalCpeDAO().save(cyberPhysicalCpe);
        }
    }

    private void generateCpsVulnerability() {
        // there has to be at least all CVE entries in the database
        if (dbm.getCveDAO().count() > 100000 && dbm.getCpsVulnerabilityDAO().count() == 0) {
            // Standard entries for CPS-CVE (Stuxnet)
            String sql = "SELECT * FROM cve WHERE description LIKE '%Stuxnet%'";

            EntityManager em = dbm.getCveDAO().getEntityManager();
            Query cveQuery = em.createNativeQuery(sql, Cve.class);
            List<Cve> cves = (List<Cve>) cveQuery.getResultList();

            // dummy for sequence of compromise
            int counter = 1;

            // if there is any stuxnet data
            if (cves
                    != null && !cves.isEmpty()) {

                CpsVulnerability cpsVulnerability = new CpsVulnerability();
                cpsVulnerability.setCveId("CPS-CVE-ID-2019-0001");
                cpsVulnerability.setDate(new Date().toString());
                cpsVulnerability.setDescription("Stuxnets ausgenutzte Gesamtschachstelle der CPSs in der Urananlagerungsanlage Natanz");
                CpsVulnerability reloadedEntry = dbm.getCpsVulnerabilityDAO().save(cpsVulnerability);

                for (Cve cve : cves) {
                    CveCpsVulnerabilityMapping ccvm = new CveCpsVulnerabilityMapping();
                    ccvm.setCpsVulnerability(reloadedEntry);
                    ccvm.setCve(cve);
                    ccvm.setSequenceOfCompromise(counter);
                    dbm.getCveCpsVulnerabilityMappingDAO().save(ccvm);
                    counter++;
                }
            }
        }
    }
}
