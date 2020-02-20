/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.dao;

import java.io.Serializable;
import cps.extended.concept.entities.Cpe;
import javax.ejb.Stateless;

/**
 *
 * @author tarnschaf
 */
@Stateless
public class CpeDAO extends GenericDAO<Cpe, Integer> implements Serializable {
}
