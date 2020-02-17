/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.beans;

import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.asset.Cpe;
import cps.extended.concept.entities.asset.CyberPhysicalCpe;
import cps.extended.concept.entities.vulnerability.Cve;
import java.io.Serializable;
import java.sql.Connection;
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

    public Connection getMySqlConnection() throws Exception {
        EntityManager em = dbm.getCapabilityDAO().getEntityManager();
        Connection conn = (Connection) em.unwrap(Connection.class);
        return conn;
    }

    /**
     * searches CVEs and assigned CPEs and CP-CPEs
     *
     */
    public void searchCves() {

        cves = new ArrayList();

        try {

            if (cveSearchString == null || cveSearchString.isEmpty()) {
                return;
            }

            // substring
            String[] searchParams = cveSearchString.split(" ");

            EntityManager em = dbm.getCapabilityDAO().getEntityManager();

            String sql = "SELECT * FROM cve WHERE ";

            for (String searchParam : searchParams) {
                if (!searchParam.isEmpty() || !searchParam.equals("") || !searchParam.contains("'")) {
                    sql += "cve_id LIKE '%" + searchParam + "%' AND ";
                }
            }

            sql = sql.substring(0, sql.length() - 4);
            sql += " OR ";

            for (String searchParam : searchParams) {
                if (!searchParam.isEmpty() || !searchParam.equals("") || !searchParam.contains("'")) {
                    sql += "description LIKE '%" + searchParam + "%' AND ";
                }
            }
            sql = sql.substring(0, sql.length() - 4);

            Query cveQuery = em.createNativeQuery(sql, Cve.class);
            List<Cve> cves = (List<Cve>) cveQuery.getResultList();

            for (Cve cve : cves) {
                long id = cve.getId();

                // get CPEs
                String sql2 = "SELECT cpe.* FROM cve cve JOIN cve_cpe vp ON vp.cve_id = cve.id JOIN cpe cpe ON cpe.id = vp.cpe_id WHERE cve.id = " + id;

                Query cpeQuery = em.createNativeQuery(sql2, Cpe.class);
                List<Cpe> cpes = (List<Cpe>) cpeQuery.getResultList();
                cve.setCyberCpes(cpes);

                // get CP-CPEs
                String sql3 = "SELECT cp_cpe.* FROM cve cve JOIN cve_cp_cpe ccc ON ccc.cyber_cve_id = cve.id JOIN cyber_physical_cpe cp_cpe ON cp_cpe.id = ccc.cyber_physical_cpe_id WHERE cve.id = " + id;

                Query cpCpeQuery = em.createNativeQuery(sql3, CyberPhysicalCpe.class);
                List<CyberPhysicalCpe> cyberPhysicalCpes = (List<CyberPhysicalCpe>) cpCpeQuery.getResultList();
                cve.setCyberPhysicalCpes(cyberPhysicalCpes);

                cve.setCyberPhysicalCpes(cyberPhysicalCpes);
            }

            this.cves = cves;

        } catch (Exception ex) {
            Logger.getLogger(CpeController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
