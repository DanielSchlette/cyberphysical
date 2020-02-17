/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.entities.asset;

import cps.extended.concept.entities.asset.attributes.Capability;
import cps.extended.concept.entities.asset.attributes.Communication;
import cps.extended.concept.entities.asset.attributes.Industry;
import cps.extended.concept.entities.asset.attributes.Part;
import cps.extended.concept.entities.asset.attributes.Protocol;
import cps.extended.concept.entities.vulnerability.Cve;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * New CP-CPE database for components in cyber-physical systems
 *
 * @author tarnschaf
 */
@Entity(name = "cyber_physical_cpe")
public class CyberPhysicalCpe extends AbstractCpe {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part")
    private Part part;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "communication")
    private Communication communication;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Capability> capabilities = new ArrayList();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Industry> industries = new ArrayList();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Protocol> protocols = new ArrayList();

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cyberPhysicalCpes")
    private List<Cve> cyberPhysicalCves = new ArrayList<>();

    public CyberPhysicalCpe() {
        super();
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Communication getCommunication() {
        return communication;
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
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

    public List<Protocol> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<Protocol> protocols) {
        this.protocols = protocols;
    }

    public List<Cve> getCyberPhysicalCves() {
        return cyberPhysicalCves;
    }

    public void setCyberPhysicalCves(List<Cve> cyberPhysicalCves) {
        this.cyberPhysicalCves = cyberPhysicalCves;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CyberPhysicalCpe)) {
            return false;
        }
        CyberPhysicalCpe other = (CyberPhysicalCpe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cps.extended.concept.entities.asset.CyberPhysicalCpe[ id=" + id + " ]";
    }

}
