/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.beans;

import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.Cve;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Search for CVEs
 *
 * @author tarnschaf
 */
@SessionScoped
@Named("cveSearchController")
public class CveController implements Serializable {

    private String cveSearchString;

    @Inject
    DbManager dbm;

    private List<Cve> cves;

    public CveController() {
    }

    public void searchCves() {
        cves = dbm.getCveDAO().searchCves(cveSearchString);
    }

    public String getCveSearchString() {
        return cveSearchString;
    }

    public void setCveSearchString(String cveSearchString) {
        this.cveSearchString = cveSearchString;
    }

    public List<Cve> getCves() {
        return cves;
    }

    public void setCves(List<Cve> cves) {
        this.cves = cves;
    }
}
