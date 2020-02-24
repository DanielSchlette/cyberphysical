/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CPE datebase entry
 *
 * @author tarnschaf
 */
@Entity(name = "cpe")
@Table(name = "cpe")
public class Cpe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected long id;

    @Column(name = "system_update")
    protected String update;

    @Column(name = "edition")
    protected String edition;

    @Column(name = "system_language")
    protected String language;

    @Column(name = "softwareEdition")
    protected String softwareEdition;

    @Column(name = "targetSoftware")
    protected String targetSoftware;

    @Column(name = "title")
    protected String title;

    @Column(name = "vendor")
    protected String vendor;

    @Column(name = "product")
    protected String product;

    @Column(name = "version")
    protected String version;

    @Column(name = "target_hardware")
    protected String targetHardware;

    @Column(name = "other")
    protected String other;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private List<Part> partlist;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private List<Capability> capabilitylist;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private List<Communication> communicationlist;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private List<ProgrammingLang> programmingLanglist;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private List<Protocol> protocollist;

    @Column(name = "cpe22")
    private String cpe22String;

    @Column(name = "cpe23")
    private String cpe23String;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private List<Industry> industrylist;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cyberCpes")
    private List<Cve> cyberCves = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cpes")
    private List<CPSBundle> bundles = new ArrayList<>();

    public final static String CPE_ATTR = "cpe";

    public Cpe() {
    }

    public void set23Uri(String cpe23) {
        //mapCPE23(cpe23);
    }

    public void mapCPE23(HashMap<String, Part> parts) {
        String cpe23 = cpe23String.substring(8);
        String[] tokens = cpe23.split(":");

        int tokenNumber = 0;

        //cpe:2.3:<part>:<vendor>:<product>:<version>:<update>:<edition>:<language>:<softwareedition><targetsoftware><targethardware><other>
        for (String token : tokens) {
            switch (tokenNumber) {
                case 0:
                    // part = parts.get(token);
                    break;
                case 1:
                    vendor = token;
                    break;
                case 2:
                    product = token;
                    break;
                case 3:
                    version = token;
                    break;
                case 4:
                    update = token;
                    break;
                case 5:
                    edition = token;
                    break;
                case 6:
                    language = token;
                    break;
                case 7:
                    softwareEdition = token;
                    break;
                case 8:
                    targetSoftware = token;
                    break;
                case 9:
                    targetHardware = token;
                    break;
                case 10:
                    other = token;
                    break;
            }
            tokenNumber++;
        }
    }

    public void set22Uri(String cpe22) {
        this.cpe22String = cpe22;
//        cpe22 = cpe22.substring(5);
//        String[] tokens = cpe22.split(":");
//
//        int tokenNumber = 0;
//
//        //<part>:<vendor>:<product>:<version>:<update>:<edition>:<language>
//        for (String token : tokens) {
//            switch (tokenNumber) {
//                case 0:
//                    part = token;
//                    break;
//                case 1:
//                    vendor = token;
//                    break;
//                case 2:
//                    product = token;
//                    break;
//                case 3:
//                    version = token;
//                    break;
//                case 4:
//                    update = token;
//                    break;
//                case 5:
//                    edition = token;
//                    break;
//                case 6:
//                    language = token;
//                    break;
//            }
//            tokenNumber++;
//        }

    }

    public void setCyberCves(List<Cve> cyberCves) {
        this.cyberCves = cyberCves;
    }

    public List<Cve> getCyberCves() {
        return cyberCves;
    }

    public String getCpe22String() {
        return cpe22String;
    }

    public void setCpe22String(String cpe22String) {
        this.cpe22String = cpe22String;
    }

    public String getCpe23String() {
        return cpe23String;
    }

    public void setCpe23String(String cpe23String) {
        this.cpe23String = cpe23String;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSoftwareEdition() {
        return softwareEdition;
    }

    public void setSoftwareEdition(String softwareEdition) {
        this.softwareEdition = softwareEdition;
    }

    public String getTargetSoftware() {
        return targetSoftware;
    }

    public void setTargetSoftware(String targetSoftware) {
        this.targetSoftware = targetSoftware;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTargetHardware() {
        return targetHardware;
    }

    public void setTargetHardware(String targetHardware) {
        this.targetHardware = targetHardware;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<CPSBundle> getBundles() {
        return bundles;
    }

    public void setBundles(List<CPSBundle> bundles) {
        this.bundles = bundles;
    }

    public List<Part> getPartlist() {
        return partlist;
    }

    public void setPartlist(List<Part> partlist) {
        this.partlist = partlist;
    }

    public List<Capability> getCapabilitylist() {
        return capabilitylist;
    }

    public void setCapabilitylist(List<Capability> capabilitylist) {
        this.capabilitylist = capabilitylist;
    }

    public List<Communication> getCommunicationlist() {
        return communicationlist;
    }

    public void setCommunicationlist(List<Communication> communicationlist) {
        this.communicationlist = communicationlist;
    }

    public List<ProgrammingLang> getProgrammingLanglist() {
        return programmingLanglist;
    }

    public void setProgrammingLanglist(List<ProgrammingLang> programmingLanglist) {
        this.programmingLanglist = programmingLanglist;
    }

    public List<Protocol> getProtocollist() {
        return protocollist;
    }

    public void setProtocollist(List<Protocol> protocollist) {
        this.protocollist = protocollist;
    }

    public List<Industry> getIndustrylist() {
        return industrylist;
    }

    public void setIndustrylist(List<Industry> industrylist) {
        this.industrylist = industrylist;
    }

}
