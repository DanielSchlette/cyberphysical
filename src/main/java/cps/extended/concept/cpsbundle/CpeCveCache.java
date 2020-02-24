/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.cpsbundle;

import cps.extended.concept.dao.DbManager;
import cps.extended.concept.entities.Cpe;
import cps.extended.concept.entities.Cve;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author tarnschaf
 */
@ApplicationScoped
@Named
public class CpeCveCache implements Serializable {

    @Inject
    DbManager dbm;

    private Map<String, Cpe> cpeList;
//    private Map<String, Cve> cveList;

    public void call() {
    }

    @PostConstruct
    public void init() {
        cpeList = dbm.getCpeDAO().findAllMap();
//        cveList = dbm.getCveDAO().findAllMap();
    }

    public Map<String, Cpe> getCpeList() {
        return cpeList;
    }

    public void setCpeList(Map<String, Cpe> cpeList) {
        this.cpeList = cpeList;
    }

//    public Map<String, Cve> getCveList() {
//        return cveList;
//    }
//
//    public void setCveList(Map<String, Cve> cveList) {
//        this.cveList = cveList;
//    }
}
