/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.dao;

import cps.extended.concept.dao.asset.CpeDAO;
import cps.extended.concept.dao.asset.CyberPhysicalCpeDAO;
import cps.extended.concept.dao.asset.attributes.CommunicationDAO;
import cps.extended.concept.dao.asset.attributes.IndustryDAO;
import cps.extended.concept.dao.asset.attributes.PartDAO;
import cps.extended.concept.dao.asset.attributes.CapabilityDAO;
import cps.extended.concept.dao.asset.attributes.ProtocolDAO;
import cps.extended.concept.dao.vulnerability.CpsVulnerabilityDAO;
import cps.extended.concept.dao.vulnerability.CveCpsVulnerabilityMappingDAO;
import cps.extended.concept.dao.vulnerability.CveDAO;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * DBManager for handeling with database
 *
 * @author tarnschaf
 */
@Stateless
public class DbManager implements Serializable {

    /* CPEs */
    @Inject
    CpeDAO cyberCpeDAO;

    /* CP-CPEs */
    @Inject
    CyberPhysicalCpeDAO cyberPhyiscalCpeDAO;

    /* CPS-CVEs */
    @Inject
    CpsVulnerabilityDAO commonVulnerabilityDAO;

    /* CVEs */
    @Inject
    CveDAO cveDAO;

    /* CPE Attributes */
    @Inject
    IndustryDAO industryDAO;

    @Inject
    PartDAO partDAO;

    @Inject
    CommunicationDAO communicationDAO;

    @Inject
    CapabilityDAO capabilityDAO;

    @Inject
    ProtocolDAO protocolDAO;

    /* Mapping between CPS-CVE and CVE */
    @Inject
    CveCpsVulnerabilityMappingDAO cveCpsVulnerabilityMappingDAO;

    public CyberPhysicalCpeDAO getCyberPhyiscalCpeDAO() {
        return cyberPhyiscalCpeDAO;
    }

    public CpsVulnerabilityDAO getCpsVulnerabilityDAO() {
        return commonVulnerabilityDAO;
    }

    public void setCommonVulnerabilityDAO(CpsVulnerabilityDAO commonVulnerabilityDAO) {
        this.commonVulnerabilityDAO = commonVulnerabilityDAO;
    }

    public void setCyberPhyiscalCpeDAO(CyberPhysicalCpeDAO cyberPhyiscalCpeDAO) {
        this.cyberPhyiscalCpeDAO = cyberPhyiscalCpeDAO;
    }

    public CpeDAO getCyberCpeDAO() {
        return cyberCpeDAO;
    }

    public void setCyberCpeDAO(CpeDAO cyberCpeDAO) {
        this.cyberCpeDAO = cyberCpeDAO;
    }

    public CveDAO getCveDAO() {
        return cveDAO;
    }

    public void setCveDAO(CveDAO cyberCveDAO) {
        this.cveDAO = cyberCveDAO;
    }

    public IndustryDAO getIndustryDAO() {
        return industryDAO;
    }

    public void setIndustryDAO(IndustryDAO industryDAO) {
        this.industryDAO = industryDAO;
    }

    public PartDAO getPartDAO() {
        return partDAO;
    }

    public void setPartDAO(PartDAO partDAO) {
        this.partDAO = partDAO;
    }

    public CommunicationDAO getCommunicationDAO() {
        return communicationDAO;
    }

    public void setCommunicationDAO(CommunicationDAO communicationDAO) {
        this.communicationDAO = communicationDAO;
    }

    public CapabilityDAO getCapabilityDAO() {
        return capabilityDAO;
    }

    public void setCapabilityDAO(CapabilityDAO capabilityDAO) {
        this.capabilityDAO = capabilityDAO;
    }

    public ProtocolDAO getProtocolDAO() {
        return protocolDAO;
    }

    public void setProtocolDAO(ProtocolDAO protocolDAO) {
        this.protocolDAO = protocolDAO;
    }

    public CveCpsVulnerabilityMappingDAO getCveCpsVulnerabilityMappingDAO() {
        return cveCpsVulnerabilityMappingDAO;
    }

    public void setCveCpsVulnerabilityMappingDAO(CveCpsVulnerabilityMappingDAO cveCpsVulnerabilityMappingDAO) {
        this.cveCpsVulnerabilityMappingDAO = cveCpsVulnerabilityMappingDAO;
    }
}
