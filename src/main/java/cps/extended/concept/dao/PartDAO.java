/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.dao;

import cps.extended.concept.entities.Part;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author tarnschaf
 */
@Stateless
public class PartDAO extends GenericDAO<Part, String> implements Serializable {

    public HashMap<String, Part> findAllMap() {
        List<Part> findAll = findAll();
        HashMap<String, Part> map = new HashMap<>();
        for (Part part : findAll) {
            map.put(part.getPart(), part);
        }
        return map;
    }

}
