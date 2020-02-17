/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.beans;

import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.asset.Cpe;
import cps.extended.concept.entities.vulnerability.Cve;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Search for CP-CPE-Entries
 *
 * @author tarnschaf
 */
@SessionScoped
@Named("cpeSearchController")
public class CpeController implements Serializable {

    private String cpeSearchString;

    @Inject
    DbManager dbm;

    List<Cpe> cpes;

    public CpeController() {
    }

    public void searchCpes() {
        try {

            if (cpeSearchString == null || cpeSearchString.isEmpty()) {
                return;
            }

            cpes = new ArrayList();

            // substring
            String[] searchParams = cpeSearchString.split(" ");

            EntityManager em = dbm.getCapabilityDAO().getEntityManager();

            String sql = "SELECT * FROM cpe WHERE ";

            if (!cpeSearchString.startsWith("cpe:2.3:")) {
                for (String searchParam : searchParams) {
                    if (!searchParam.isEmpty() || !searchParam.equals("") || !searchParam.contains("'")) {
                        sql += "cpe.cpe22 LIKE '%" + searchParam + "%' AND ";
                    }
                }
            }
            if (!cpeSearchString.startsWith("cpe:/")) {
                for (String searchParam : searchParams) {
                    if (!searchParam.isEmpty() || !searchParam.equals("") || !searchParam.contains("'")) {
                        sql += "cpe.cpe23 LIKE '%" + searchParam + "%' AND ";
                    }
                }
            }
            sql = sql.substring(0, sql.length() - 4);

            Query cpeQuery = em.createNativeQuery(sql, Cpe.class);
            List<Cpe> cpes = (List<Cpe>) cpeQuery.getResultList();

            for (Cpe cpe : cpes) {

                Long cpeId = cpe.getId();

                // get CVEs
                String sql2 = "SELECT cve.* FROM cve cve JOIN cve_cpe vp ON vp.cve_id = cve.id JOIN cpe cpe ON cpe.id = vp.cpe_id WHERE cpe.id = " + cpeId;

                Query cveQuery = em.createNativeQuery(sql2, Cve.class);
                List<Cve> cves = (List<Cve>) cveQuery.getResultList();

                cpe.setCyberCves(cves);

            }

            this.cpes = cpes;

        } catch (Exception ex) {
            Logger.getLogger(CpeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCpeSearchString(String cpeSearchString) {
        this.cpeSearchString = cpeSearchString;
    }

    public String getCpeSearchString() {
        return cpeSearchString;
    }

    public List<Cpe> getCpes() {
        return cpes;
    }

    public void setCpes(List<Cpe> cpes) {
        this.cpes = cpes;
    }
}
