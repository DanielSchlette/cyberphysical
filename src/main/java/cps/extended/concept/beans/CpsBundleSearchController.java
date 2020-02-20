/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.beans;

import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.CPSBundle;
import cps.extended.concept.entities.Cpe;
import cps.extended.concept.entities.Cve;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 *
 * @author tarnschaf
 */
@SessionScoped
@Named
public class CpsBundleSearchController implements Serializable {

    @Inject
    DbManager dbm;

    String searchTerm;

    List<CPSBundle> results = new ArrayList<>();

    public void search() {
        results = new ArrayList<>();;
        List<CPSBundle> allbundles = dbm.getCpsbundleDAO().findAll();

        for (CPSBundle allbundle : allbundles) {
            if (containsString(searchTerm, allbundle)) {
                results.add(fillBundle(allbundle));
            }
        }
        System.err.println("");
    }

    private CPSBundle fillBundle(CPSBundle allbundle) {

        for (Cpe cpe : allbundle.getCpes()) {

            EntityManager em = dbm.getCpeDAO().getEntityManager();
            List<Long> resultList = em.createNativeQuery("SELECT cve_id FROM cve_cpe WHERE cpe_id = " + cpe.getId()).getResultList();

            for (Long id : resultList) {
                cpe.getCyberCves().add(dbm.getCveDAO().findById(id));
            }

        }
        System.err.println("");
        return allbundle;
    }

    private boolean containsString(String term, CPSBundle bundle) {

        if (bundle.getDescription().contains(term)) {
            return true;
        }

        List<Cpe> cpes = bundle.getCpes();

        for (Cpe cpe : cpes) {
            if (cpe.getCpe23String().contains(term)) {
                return true;
            }
        }

        for (Cpe cpe : cpes) {
            List<Cve> cyberCves = cpe.getCyberCves();
            for (Cve cyberCve : cyberCves) {
                if (cyberCve.getDescription().contains(term)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public List<CPSBundle> getResults() {
        return results;
    }

    public void setResults(List<CPSBundle> results) {
        this.results = results;
    }

}
