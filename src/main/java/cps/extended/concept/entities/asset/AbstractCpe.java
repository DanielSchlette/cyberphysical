/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.entities.asset;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 * Abstract class for all types of assets in CPSs (Cyber, Cyber-physisal)
 *
 * @author tarnschaf
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AbstractCpe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

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

    public AbstractCpe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUpdate() {
        return update;
    }

    public String getEdition() {
        return edition;
    }

    public String getLanguage() {
        return language;
    }

    public String getSoftwareEdition() {
        return softwareEdition;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setSoftwareEdition(String softwareEdition) {
        this.softwareEdition = softwareEdition;
    }

    public void setTargetSoftware(String targetSoftware) {
        this.targetSoftware = targetSoftware;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTargetSoftware() {
        return targetSoftware;
    }

    public String getTitle() {
        return title;
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
        if (!(object instanceof AbstractCpe)) {
            return false;
        }
        AbstractCpe other = (AbstractCpe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cps.extended.concept.entities.asset.AbstractCpe[ id=" + id + " ]";
    }
}
