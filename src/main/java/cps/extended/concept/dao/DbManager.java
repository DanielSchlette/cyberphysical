/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.dao;

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

    @Inject
    ProgrammingLangDAO programmingLangDAO;

    @Inject
    CPSBundleDAO cpsbundleDAO;

    /* CPEs */
    @Inject
    CpeDAO cpeDAO;

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

    public ProgrammingLangDAO getProgrammingLangDAO() {
        return programmingLangDAO;
    }

    public void setProgrammingLangDAO(ProgrammingLangDAO programmingLangDAO) {
        this.programmingLangDAO = programmingLangDAO;
    }

    public CpeDAO getCpeDAO() {
        return cpeDAO;
    }

    public void setCpeDAO(CpeDAO cpeDAO) {
        this.cpeDAO = cpeDAO;
    }

    public CPSBundleDAO getCpsbundleDAO() {
        return cpsbundleDAO;
    }

    public void setCpsbundleDAO(CPSBundleDAO cpsbundleDAO) {
        this.cpsbundleDAO = cpsbundleDAO;
    }

}
