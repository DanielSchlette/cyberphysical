/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.components.cve;

import cps.extended.concept.entities.vulnerability.Cve;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tarnschaf
 */
@Stateless
public class CveFacade extends AbstractFacade<Cve> {

    @PersistenceContext(unitName = "com.ifs_KickerPlan_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CveFacade() {
        super(Cve.class);
    }
    
}
