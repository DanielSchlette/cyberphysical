/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.cpsbundle;

import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.CPSBundle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author tarnschaf
 */
@SessionScoped
@Named
public class BundleController implements Serializable {

    @Inject
    DbManager dbm;

    String currentCpe;
    String description;

    @Inject
    CpeCveCache cache;

    private CPSBundle lastBundle;

    private CPSBundle cpsBundle;

    @PostConstruct
    public void init() {
        cpsBundle = new CPSBundle();
    }

    public void saveBundle() {
        lastBundle = dbm.getCpsbundleDAO().persist(cpsBundle);
        this.description = "";
        this.cpsBundle = new CPSBundle();

    }

    public void updateDescription() {
        lastBundle = null;
        cpsBundle.setDescription(description);
    }

    public void addCPE() {
        lastBundle = null;

        if (cpsBundle.getCpes().contains(cache.getCpeList().get(currentCpe))) {
            return;
        }

        cpsBundle.getCpes().add(cache.getCpeList().get(currentCpe));

        currentCpe = "";
    }

    public List<String> querycpe(String query) {

        System.err.println("Query: " + query);
        Set<String> keySet = cache.getCpeList().keySet();

        System.err.println("Dataset size: " + keySet.size());

        List<String> results = new ArrayList<>();

        for (String string : keySet) {
            if (string.contains(query)) {
                results.add(string);
            }
        }
        System.err.println("done");
        return results;
    }

    public void save() {
        dbm.getCpsbundleDAO().persist(cpsBundle);
        cpsBundle = new CPSBundle();
    }

    public CPSBundle getCpsBundle() {
        return cpsBundle;
    }

    public void setCpsBundle(CPSBundle cpsBundle) {
        this.cpsBundle = cpsBundle;
    }

    public String getCurrentCpe() {
        return currentCpe;
    }

    public void setCurrentCpe(String currentCpe) {
        this.currentCpe = currentCpe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CPSBundle getLastBundle() {
        return lastBundle;
    }

    public void setLastBundle(CPSBundle lastBundle) {
        this.lastBundle = lastBundle;
    }

}
