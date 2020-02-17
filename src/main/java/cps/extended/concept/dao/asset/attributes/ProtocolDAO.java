/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.dao.asset.attributes;

import cps.extended.concept.dao.generic.GenericDAO;
import cps.extended.concept.entities.asset.attributes.Protocol;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author tarnschaf
 */
@Stateless
public class ProtocolDAO extends GenericDAO<Protocol, Integer> implements Serializable {
}
