/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.dao;

import java.io.Serializable;
import cps.extended.concept.entities.Cpe;
import cps.extended.concept.entities.Part;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author tarnschaf
 */
@Stateless
public class CpeDAO extends GenericDAO<Cpe, Integer> implements Serializable {

    public HashMap<String, Cpe> findAllMap() {
        List<Cpe> findAll = findAll();
        HashMap<String, Cpe> map = new HashMap<>();
        for (Cpe cpe : findAll) {
            map.put(cpe.getCpe23String(), cpe);
        }
        return map;
    }

}
