/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.beans;

import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.asset.CyberPhysicalCpe;
import cps.extended.concept.entities.asset.attributes.Capability;
import cps.extended.concept.entities.asset.attributes.Industry;
import cps.extended.concept.entities.asset.attributes.Protocol;
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
 * Search for cyber-physical components
 *
 * @author tarnschaf
 */
@SessionScoped
@Named("cyberPhysicalCpeSearchController")
public class CyberPhysicalCpeController implements Serializable {

    private String cpCpeSearchString;

    private List<CyberPhysicalCpe> cyberPhysicalCpes;

    @Inject
    DbManager dbm;

    public CyberPhysicalCpeController() {
    }

    public Connection getMySqlConnection() throws Exception {
        EntityManager em = dbm.getCapabilityDAO().getEntityManager();

        Connection conn = (Connection) em.unwrap(Connection.class);
        return conn;
    }

    public void searchCpes() {
        try {
            if (cpCpeSearchString == null || cpCpeSearchString.isEmpty()) {
                return;
            }

            cyberPhysicalCpes = new ArrayList();

            // substring
            String[] searchParams = cpCpeSearchString.split(" ");

            EntityManager em = dbm.getCapabilityDAO().getEntityManager();

            String sql = "SELECT * FROM cyber_physical_cpe WHERE ";

            sql = setSearchParams(sql, searchParams, "edition");
            sql = setSearchParams(sql, searchParams, "system_language");
            sql = setSearchParams(sql, searchParams, "other");
            sql = setSearchParams(sql, searchParams, "product");
            sql = setSearchParams(sql, searchParams, "softwareEdition");
            sql = setSearchParams(sql, searchParams, "target_hardware");
            sql = setSearchParams(sql, searchParams, "targetSoftware");
            sql = setSearchParams(sql, searchParams, "title");
            sql = setSearchParams(sql, searchParams, "system_update");
            sql = setSearchParams(sql, searchParams, "vendor");
            sql = setSearchParams(sql, searchParams, "version");

            sql = sql.substring(0, sql.length() - 4);

            Query cyberPhysicalQuery = em.createNativeQuery(sql, CyberPhysicalCpe.class);
            List<CyberPhysicalCpe> cyberPhysicalCpes = (List<CyberPhysicalCpe>) cyberPhysicalQuery.getResultList();

            for (CyberPhysicalCpe cyberPhysicalCpe : cyberPhysicalCpes) {

                Long cyberPhysicalCpeId = cyberPhysicalCpe.getId();

                // get CVEs
                String sql2 = "SELECT cve.* FROM cve cve JOIN cve_cp_cpe ccc ON ccc.cyber_cve_id = cve.id JOIN cyber_physical_cpe cpcpe ON cpcpe.id = ccc.cyber_physical_cpe_id WHERE cpcpe.id = " + cyberPhysicalCpeId;

                Query cveQuery = em.createNativeQuery(sql2, Cve.class);
                List<Cve> cves = (List<Cve>) cveQuery.getResultList();

                cyberPhysicalCpe.setCyberPhysicalCves(cves);

                // get capabilities
                String sql3 = "SELECT c.* FROM capability c JOIN cyber_physical_cpe_capability cpcc"
                        + " ON c.id = cpcc.capabilities_id JOIN cyber_physical_cpe cpcpe ON cpcpe.id = cpcc.cyber_physical_cpe_id WHERE cpcpe.id = " + cyberPhysicalCpeId;

                Query capabilityQuery = em.createNativeQuery(sql3, Capability.class);
                List<Capability> capabilities = (List<Capability>) capabilityQuery.getResultList();

                cyberPhysicalCpe.setCapabilities(capabilities);

                // get industries
                String sql4 = "SELECT i.* FROM industry i JOIN cyber_physical_cpe_industry cpci"
                        + " ON i.id = cpci.industries_id JOIN cyber_physical_cpe cpcpe ON cpcpe.id = cpci.cyber_physical_cpe_id WHERE cpcpe.id = " + cyberPhysicalCpeId;

                Query industryQuery = em.createNativeQuery(sql4, Industry.class);
                List<Industry> industries = (List<Industry>) industryQuery.getResultList();

                cyberPhysicalCpe.setIndustries(industries);

                // get capabilities
                String sql5 = "SELECT p.* FROM protocol p JOIN cyber_physical_cpe_protocol cpcp"
                        + " ON p.id = cpcp.protocols_id JOIN cyber_physical_cpe cpcpe ON cpcpe.id = cpcp.cyber_physical_cpe_id WHERE cpcpe.id = " + cyberPhysicalCpeId;

                Query protocolQuery = em.createNativeQuery(sql5, Protocol.class);
                List<Protocol> protocols = (List<Protocol>) protocolQuery.getResultList();

                cyberPhysicalCpe.setProtocols(protocols);

            }

            this.cyberPhysicalCpes = cyberPhysicalCpes;

        } catch (Exception ex) {
            Logger.getLogger(CpeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String setSearchParams(String sql, String[] searchParams, String column) {
        for (String searchParam : searchParams) {
            if (!searchParam.isEmpty() || !searchParam.equals("") || !searchParam.contains("'")) {
                sql += "cyber_physical_cpe." + column + " LIKE '%" + searchParam + "%' AND ";
            }
        }
        sql = sql.substring(0, sql.length() - 4);
        sql += " OR ";
        return sql;
    }

    public String getCpCpeSearchString() {
        return cpCpeSearchString;
    }

    public void setCpCpeSearchString(String cpCpeSearchString) {
        this.cpCpeSearchString = cpCpeSearchString;
    }

    public List<CyberPhysicalCpe> getCpcpes() {
        return cyberPhysicalCpes;
    }

    public void setCpcpes(List<CyberPhysicalCpe> cpcpes) {
        this.cyberPhysicalCpes = cpcpes;
    }
}
