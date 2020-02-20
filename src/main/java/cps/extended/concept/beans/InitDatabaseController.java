/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.beans;

import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.Capability;
import cps.extended.concept.entities.Communication;
import cps.extended.concept.entities.Cpe;
import cps.extended.concept.entities.Industry;
import cps.extended.concept.entities.Part;
import cps.extended.concept.entities.ProgrammingLang;
import cps.extended.concept.entities.Protocol;
import cps.extended.concept.enums.CapabilityPresettings;
import cps.extended.concept.enums.CommunicationPresettings;
import cps.extended.concept.enums.IndustryPresettings;
import cps.extended.concept.enums.PartPresettings;
import cps.extended.concept.enums.ProtocolPresettings;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Initializes the default database settings
 *
 * @author tarnschaf
 */
@ViewScoped
@Named("initDatabaseController")
public class InitDatabaseController implements Serializable {

    @Inject
    DbManager dbm;

    public InitDatabaseController() {
    }

    public void parseDatabase() {
        HashMap<String, Part> parts = dbm.getPartDAO().findAllMap();
        List<Cpe> allcpes = dbm.getCpeDAO().findAll();
        for (Cpe cpe : allcpes) {
            cpe.mapCPE23(parts);
            dbm.getCpeDAO().persist(cpe);
        }

    }

    public void initCyberPhysicalCpeDatabase() {

        // Initializes default part settings
        if (dbm.getPartDAO().count() == 0) {
            for (PartPresettings setting : PartPresettings.values()) {
                Part part = new Part();
                part.setPart(setting.getPart());
                dbm.getPartDAO().persist(part);
            }
        }

        // Initializes default communication settings
        if (dbm.getCommunicationDAO().count() == 0) {
            for (CommunicationPresettings presettings : CommunicationPresettings.values()) {
                Communication communication = new Communication();
                communication.setCommunication(presettings.getCommunication());
                dbm.getCommunicationDAO().persist(communication);
            }
        }

        // Initializes default capability settings
        if (dbm.getCapabilityDAO().count() == 0) {
            for (CapabilityPresettings capabilityPresettings : CapabilityPresettings.values()) {
                Capability capability = new Capability();
                capability.setCapability(capabilityPresettings.getCapability());
                dbm.getCapabilityDAO().persist(capability);
            }
        }

        // Initializes default protocol settings
        if (dbm.getProtocolDAO().count() == 0) {
            for (ProtocolPresettings protocolPresettings : ProtocolPresettings.values()) {
                Protocol protocol = new Protocol();
                protocol.setProtocol(protocolPresettings.getProtocol());
                dbm.getProtocolDAO().persist(protocol);
            }
        }

        // Initializes default industry settings
        if (dbm.getIndustryDAO().count() == 0) {
            for (IndustryPresettings industryPresettings : IndustryPresettings.values()) {
                Industry industry = new Industry();
                industry.setIndustry(industryPresettings.getIndustry());
                dbm.getIndustryDAO().persist(industry);
            }
        }
    }

}
