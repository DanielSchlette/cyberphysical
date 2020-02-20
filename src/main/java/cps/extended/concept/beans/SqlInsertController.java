/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.beans;

import cps.extended.concept.cpe.sql.CpeDatabaseRetriever;
import cps.extended.concept.cpe.sql.CpeHandler;
import cps.extended.concept.cpe.sql.CveCpeHandler;
import cps.extended.concept.cpe.sql.CveHandler;
import cps.extended.concept.dao.DbManager;
import cps.extended.concept.directory.DirectoryUtil;
import cps.extended.concept.entities.Cpe;
import cps.extended.concept.entities.Cve;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import us.springett.nistdatamirror.NistDataMirror;

/**
 * Retrieves all needed data from MITRE und NVD
 *
 * @author tarnschaf
 */
@SessionScoped
@Named("sqlController")
public class SqlInsertController implements Serializable {

    @Inject
    DbManager dbm;

    public Connection getMySqlConnection() throws Exception {
        String driver = "org.gjt.mm.mysql.Driver";
        String url = "jdbc:mysql://localhost/cpsconcept";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public void insert() {
        try {
            Connection conn = null;
            Statement stmt;
            try {
                conn = getMySqlConnection();
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                conn.setAutoCommit(false);

                DirectoryUtil.createProjectFolder();
                File projectFolder = DirectoryUtil.getProjectFolder();
                System.out.println("Directory file: " + projectFolder.getAbsolutePath());

                CpeDatabaseRetriever retriever = new CpeDatabaseRetriever(projectFolder.getAbsolutePath(), "xml");

                retriever.mirror();

                File[] listOfFiles = projectFolder.listFiles();

                HashMap<String, Long> matchToCpeId = new HashMap();
                HashMap<String, Long> matchToCveId = new HashMap();

                stmt.addBatch("SET autocommit=0;");
                stmt.addBatch("SET unique_checks=0;");
                stmt.addBatch("SET foreign_key_checks=0;");

                stmt.addBatch("TRUNCATE TABLE cpe");
                stmt.addBatch("TRUNCATE TABLE cve");
//                stmt.addBatch("TRUNCATE TABLE cps_vulnerability");
//                stmt.addBatch("TRUNCATE TABLE cve_cps_vulnerability_mapping");

                // at least because many time is needed
                stmt.addBatch("TRUNCATE TABLE cve_cpe");

                stmt.executeBatch();

                int counter = 0;
                for (File listOfFile : listOfFiles) {
                    if (listOfFile.isFile() && listOfFile.getAbsolutePath().endsWith("official-cpe-dictionary_v2.3.xml")) {

                        try {
                            SAXParserFactory factory = SAXParserFactory.newInstance();
                            SAXParser saxParser = factory.newSAXParser();

                            CpeHandler cpeHandler = new CpeHandler();
                            saxParser.parse(listOfFile, cpeHandler);

                            List<Cpe> cpes = cpeHandler.getCpeList();

                            for (Cpe cpe : cpes) {
                                String sql = "INSERT INTO cpe(id, cpe22, cpe23, title)"
                                        + " VALUES ('" + counter + "', '" + cpe.getCpe22String() + "', '" + cpe.getCpe23String() + "', '" + cpe.getTitle() + "')";

                                matchToCpeId.put(cpe.getCpe22String(), new Long(counter));

                                stmt.addBatch(sql);

                                counter++;
                            }
                        } catch (ParserConfigurationException | SAXException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                stmt.executeBatch();

                stmt.addBatch("SET autocommit=1;");
                stmt.addBatch("SET unique_checks=1;");
                stmt.addBatch("SET foreign_key_checks=1;");

                stmt.executeBatch();

                conn.commit();

                NistDataMirror dataMirror = new NistDataMirror(projectFolder.getAbsolutePath(), "xml");
                dataMirror.mirror();

                conn = getMySqlConnection();
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                conn.setAutoCommit(false);

                stmt.addBatch("SET autocommit=0;");
                stmt.addBatch("SET unique_checks=0;");
                stmt.addBatch("SET foreign_key_checks=0;");

                stmt.executeBatch();

                for (File listOfFile : listOfFiles) {
                    if (listOfFile.isFile() && listOfFile.getAbsolutePath().contains("nvdcve-2.0") && listOfFile.getAbsolutePath().endsWith(".xml")) {
                        SAXParserFactory factory = SAXParserFactory.newInstance();
                        try {

                            System.out.println(listOfFile);

                            factory.setNamespaceAware(true);
                            SAXParser saxParser = factory.newSAXParser();
                            CveHandler cveHandler = new CveHandler();
                            saxParser.parse(listOfFile, cveHandler);
                            List<Cve> cves = cveHandler.getCveList();

                            for (Cve cve : cves) {

                                stmt.addBatch("INSERT INTO cve(id, cve_id, description, date, source) VALUES ('" + counter + "', '" + cve.getCveId() + "', '" + cve.getDescription() + "' , '" + cve.getDate() + "', '" + cve.getSource() + "')");

                                matchToCveId.put(cve.getCveId(), new Long(counter));
                                counter++;

                            }

                        } catch (ParserConfigurationException | SAXException | IOException ex) {
                            Logger.getLogger(CpeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                stmt.executeBatch();

                stmt.addBatch("SET autocommit=1;");
                stmt.addBatch("SET unique_checks=1;");
                stmt.addBatch("SET foreign_key_checks=1;");

                stmt.executeBatch();
                conn.commit();

                for (File listOfFile : listOfFiles) {
                    if (listOfFile.isFile() && listOfFile.getAbsolutePath().contains("nvdcve-2.0") && listOfFile.getAbsolutePath().endsWith(".xml")) {
                        SAXParserFactory factory = SAXParserFactory.newInstance();
                        try {
                            System.out.println(listOfFile);

                            factory.setNamespaceAware(true);
                            SAXParser saxParser = factory.newSAXParser();

                            CveCpeHandler cveCpeHandler = new CveCpeHandler(matchToCpeId, matchToCveId);
                            saxParser.parse(listOfFile, cveCpeHandler);
                            Map<Long, List<Long>> mappings = cveCpeHandler.getMap();

                            conn = getMySqlConnection();
                            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE);
                            conn.setAutoCommit(false);

                            stmt.addBatch("SET autocommit=0;");
                            stmt.addBatch("SET unique_checks=0;");
                            stmt.addBatch("SET foreign_key_checks=0;");

                            stmt.executeBatch();

                            for (Map.Entry<Long, List<Long>> entrySet : mappings.entrySet()) {
                                Long cveId = entrySet.getKey();
                                List<Long> cpeList = entrySet.getValue();

                                for (Long cpeId : cpeList) {
                                    if (cveId != null && cpeId != null) {
                                        stmt.addBatch("DELETE FROM cve_cpe WHERE cve_id = " + cveId + " AND cpe_id = " + cpeId);
                                        stmt.addBatch("INSERT INTO cve_cpe(cve_id, cpe_id) VALUES ('" + cveId + "', '" + cpeId + "')");
                                    }
                                }
                            }

                            stmt.executeBatch();

                            stmt.addBatch("SET autocommit=1;");
                            stmt.addBatch("SET unique_checks=1;");
                            stmt.addBatch("SET foreign_key_checks=1;");

                            stmt.executeBatch();
                            conn.commit();

                        } catch (ParserConfigurationException | SAXException | IOException ex) {
                            Logger.getLogger(CpeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            } catch (BatchUpdateException b) {
                System.err.println("SQLException: " + b.getMessage());
                System.err.println("SQLState: " + b.getSQLState());
                System.err.println("Message: " + b.getMessage());
                System.err.println("Vendor error code: " + b.getErrorCode());
                System.err.print("Update counts: ");
                int[] updateCounts = b.getUpdateCounts();
                for (int i = 0; i < updateCounts.length; i++) {
                    System.err.print(updateCounts[i] + " ");
                }
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
                System.err.println("SQLState: " + ex.getSQLState());
                System.err.println("Message: " + ex.getMessage());
                System.err.println("Vendor error code: " + ex.getErrorCode());
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Exception: " + e.getMessage());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SqlInsertController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(SqlInsertController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
