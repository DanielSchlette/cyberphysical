/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.dao;

import com.uaihebert.uaicriteria.UaiCriteria;
import java.io.Serializable;
import cps.extended.concept.entities.Cve;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author tarnschaf
 */
@Stateless
public class CveDAO extends GenericDAO<Cve, Long> implements Serializable {

    public List<Cve> searchCves(String term) {
        UaiCriteria<Cve> crit = getCrit();
        crit.orStringLike("cveId", "%" + term + "%");
        crit.orStringLike("description", "%" + term + "%");
        return crit.getResultList();
    }

    public HashMap<String, Cve> findAllMap() {
        List<Cve> findAll = findAll();
        HashMap<String, Cve> map = new HashMap<>();
        for (Cve cve : findAll) {
            map.put(cve.getCveId(), cve);
        }
        return map;
    }

}
