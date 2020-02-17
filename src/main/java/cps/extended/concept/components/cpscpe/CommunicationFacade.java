/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.components.cpscpe;

import cps.extended.concept.entities.asset.attributes.Communication;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tarnschaf
 */
@Stateless
public class CommunicationFacade extends AbstractFacade<Communication> {

    @PersistenceContext(unitName = "com.ifs_KickerPlan_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommunicationFacade() {
        super(Communication.class);
    }
    
}
