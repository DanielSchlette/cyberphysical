/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.dao;

import java.io.Serializable;
import cps.extended.concept.entities.Cve;
import javax.ejb.Stateless;

/**
 *
 * @author tarnschaf
 */
@Stateless
public class CveDAO extends GenericDAO<Cve, Integer> implements Serializable {
}