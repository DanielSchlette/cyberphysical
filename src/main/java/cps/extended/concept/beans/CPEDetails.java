/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.beans;

import cps.extended.concept.cpsbundle.CpeCveCache;
import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.Cpe;
import cps.extended.concept.entities.Cve;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
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
public class CPEDetails implements Serializable {

    @Inject
    DbManager dbm;
    Cpe currentCPE;

    @Inject
    CpeCveCache cache;

    String currentCve;

    @PostConstruct
    public void init() {
        currentCPE = retrieveCpe();
        checkDelete();

    }

    private Cpe retrieveCpe() {
        String cpeid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cpe");

        if (cpeid == null) {
            return null;
        } else {
            try {
                return dbm.getCpeDAO().findById(Long.parseLong(cpeid));
            } catch (NumberFormatException ex) {
                return null;
            }
        }
    }

    public List<String> querycve(String query) {

        System.err.println("Query: " + query);
        Set<String> keySet = cache.getCveList().keySet();

        System.err.println("Dataset size: " + keySet.size());
        List<String> results = new ArrayList<>();

        for (String string : keySet) {
            if (string.contains(query)) {
                results.add(string);
            }
        }
        return results;
    }

    public void addCVE() {

        if (currentCPE.getCyberCves().contains(cache.getCveList().get(currentCve))) {
            return;
        }

        currentCPE = dbm.getCpeDAO().findById(currentCPE.getId());
        currentCPE.getCyberCves().add(cache.getCveList().get(currentCve));

        for (Cve cyberCve : currentCPE.getCyberCves()) {

            if (!cyberCve.getCyberCpes().contains(currentCPE)) {
                cyberCve.getCyberCpes().add(currentCPE);
                dbm.getCveDAO().persist(cyberCve);
            }

        }

        currentCPE = dbm.getCpeDAO().persist(currentCPE);

        currentCve = "";
    }

    public Cpe getCurrentCPE() {
        return currentCPE;
    }

    public void setCurrentCPE(Cpe currentCPE) {
        this.currentCPE = currentCPE;
    }

    private void checkDelete() {

        String delete = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("delete");

        if ("true".equals(delete)) {
            if (currentCPE != null) {
                dbm.getCpeDAO().delete(currentCPE);
            }

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + "/cpesearch.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(CPEDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getCurrentCve() {
        return currentCve;
    }

    public void setCurrentCve(String currentCve) {
        this.currentCve = currentCve;
    }

}
