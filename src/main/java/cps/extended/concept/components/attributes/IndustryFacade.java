/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.components.attributes;

import cps.extended.concept.entities.Industry;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tarnschaf
 */
@Stateless
public class IndustryFacade extends AbstractFacade<Industry> {

    @PersistenceContext(unitName = "com.ifs_KickerPlan_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IndustryFacade() {
        super(Industry.class);
    }
    
}
