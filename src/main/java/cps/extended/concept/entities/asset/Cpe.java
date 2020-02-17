/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.entities.asset;

import cps.extended.concept.entities.vulnerability.Cve;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 * CPE datebase entry
 *
 * @author tarnschaf
 */
@Entity(name = "cpe")
public class Cpe extends AbstractCpe {

    @Column(name = "part")
    private String part;

    @Column(name = "cpe22")
    private String cpe22String;

    @Column(name = "cpe23")
    private String cpe23String;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cyberCpes")
    private List<Cve> cyberCves = new ArrayList<>();

    public final static String CPE_ATTR = "cpe";

    public Cpe() {
    }

    public void set23Uri(String cpe23) {
        this.cpe23String = cpe23;
        cpe23 = cpe23.substring(8);
        String[] tokens = cpe23.split(":");

        int tokenNumber = 0;

        //cpe:2.3:<part>:<vendor>:<product>:<version>:<update>:<edition>:<language>:<softwareedition><targetsoftware><targethardware><other>
        for (String token : tokens) {
            switch (tokenNumber) {
                case 0:
                    part = token;
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
        cpe22 = cpe22.substring(5);
        String[] tokens = cpe22.split(":");

        int tokenNumber = 0;

        //<part>:<vendor>:<product>:<version>:<update>:<edition>:<language>
        for (String token : tokens) {
            switch (tokenNumber) {
                case 0:
                    part = token;
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
            }
            tokenNumber++;
        }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cpe)) {
            return false;
        }
        Cpe other = (Cpe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ifs.kickerplan.entities.CyberCpe[ id=" + id + " ]";
    }

}
