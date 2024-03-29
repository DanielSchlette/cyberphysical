/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.beans;

import cps.extended.concept.cpsbundle.CpeCveCache;
import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.Capability;
import cps.extended.concept.entities.Communication;
import cps.extended.concept.entities.Cpe;
import cps.extended.concept.entities.Industry;
import cps.extended.concept.entities.Part;
import cps.extended.concept.entities.ProgrammingLang;
import cps.extended.concept.entities.Protocol;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author tarnschaf
 */
@ViewScoped
@Named
public class CpeCreateController implements Serializable {

    @Inject
    DbManager dbm;

    private List<Part> parts;
    private List<Communication> communications;
    private List<Capability> capabilities;
    private List<Industry> industries;
    private List<ProgrammingLang> programminglangs;
    private List<Protocol> protocols;

    private String[] part;
    private String[] communication;
    private String[] capability;
    private String[] industry;
    private String[] programmingLang;
    private String[] protocol;

    Cpe cpe;

    @Inject
    CpeCveCache cache;

    @PostConstruct
    public void init() {
        cpe = new Cpe();
        parts = dbm.getPartDAO().findAll();
        communications = dbm.getCommunicationDAO().findAll();
        capabilities = dbm.getCapabilityDAO().findAll();
        industries = dbm.getIndustryDAO().findAll();
        programminglangs = dbm.getProgrammingLangDAO().findAll();
        protocols = dbm.getProtocolDAO().findAll();
    }

    public void save() {
        if (part != null) {
            for (Part i : parts) {
                for (String s : part) {
                    if (s.equals(i.getPart())) {
                        if (cpe.getPartlist() == null) {
                            cpe.setPartlist(new ArrayList<Part>());
                        }
                        cpe.getPartlist().add(i);
                    }
                }
            }
        }

        if (communication != null) {
            for (Communication i : communications) {
                for (String s : communication) {
                    if (s.equals(i.getCommunication())) {
                        if (cpe.getCommunicationlist() == null) {
                            cpe.setCommunicationlist(new ArrayList<Communication>());
                        }
                        cpe.getCommunicationlist().add(i);
                    }
                }
            }
        }

        if (capability != null) {
            for (Capability i : capabilities) {
                for (String s : capability) {
                    if (s.equals(i.getCapability())) {
                        if (cpe.getCapabilitylist() == null) {
                            cpe.setCapabilitylist(new ArrayList<Capability>());
                        }
                        cpe.getCapabilitylist().add(i);
                    }
                }
            }
        }

        if (industry != null) {
            for (Industry i : industries) {
                for (String s : industry) {
                    if (s.equals(i.getIndustry())) {
                        if (cpe.getIndustrylist() == null) {
                            cpe.setIndustrylist(new ArrayList<Industry>());
                        }
                        cpe.getIndustrylist().add(i);
                    }
                }
            }
        }

        if (programmingLang != null) {
            for (ProgrammingLang i : programminglangs) {
                for (String s : programmingLang) {
                    if (s.equals(i.getLanguage())) {
                        if (cpe.getProgrammingLanglist() == null) {
                            cpe.setProgrammingLanglist(new ArrayList<ProgrammingLang>());
                        }
                        cpe.getProgrammingLanglist().add(i);
                    }
                }
            }
        }

        if (protocol != null) {
            for (Protocol i : protocols) {
                for (String s : protocol) {
                    if (s.equals(i.getProtocol())) {
                        if (cpe.getProtocollist() == null) {
                            cpe.setProtocollist(new ArrayList<Protocol>());
                        }
                        cpe.getProtocollist().add(i);
                    }
                }
            }
        }

        Cpe persisted = dbm.getCpeDAO().persist(cpe);

        cache.addToCpe(persisted);

        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "CPE created successfully", "");
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);

    }

    public Cpe getCpe() {
        return cpe;
    }

    public void setCpe(Cpe cpe) {
        this.cpe = cpe;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public DbManager getDbm() {
        return dbm;
    }

    public void setDbm(DbManager dbm) {
        this.dbm = dbm;
    }

    public List<Communication> getCommunications() {
        return communications;
    }

    public void setCommunications(List<Communication> communications) {
        this.communications = communications;
    }

    public List<Capability> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<Capability> capabilities) {
        this.capabilities = capabilities;
    }

    public List<Industry> getIndustries() {
        return industries;
    }

    public void setIndustries(List<Industry> industries) {
        this.industries = industries;
    }

    public List<ProgrammingLang> getProgramminglangs() {
        return programminglangs;
    }

    public void setProgramminglangs(List<ProgrammingLang> programminglangs) {
        this.programminglangs = programminglangs;
    }

    public List<Protocol> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<Protocol> protocols) {
        this.protocols = protocols;
    }

    public String[] getPart() {
        return part;
    }

    public void setPart(String[] part) {
        this.part = part;
    }

    public String[] getCommunication() {
        return communication;
    }

    public void setCommunication(String[] communication) {
        this.communication = communication;
    }

    public String[] getCapability() {
        return capability;
    }

    public void setCapability(String[] capability) {
        this.capability = capability;
    }

    public String[] getIndustry() {
        return industry;
    }

    public void setIndustry(String[] industry) {
        this.industry = industry;
    }

    public String[] getProgrammingLang() {
        return programmingLang;
    }

    public void setProgrammingLang(String[] programmingLang) {
        this.programmingLang = programmingLang;
    }

    public String[] getProtocol() {
        return protocol;
    }

    public void setProtocol(String[] protocol) {
        this.protocol = protocol;
    }

}
