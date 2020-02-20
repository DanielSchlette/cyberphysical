/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.beans;

import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.asset.attributes.Capability;
import cps.extended.concept.entities.asset.attributes.Communication;
import cps.extended.concept.entities.asset.attributes.Industry;
import cps.extended.concept.entities.asset.attributes.Part;
import cps.extended.concept.entities.asset.attributes.Protocol;
import cps.extended.concept.entities.Cve;
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
    }

}
