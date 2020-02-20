/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.dao;

import cps.extended.concept.EasyCriteria.EasyCritImpl;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.criteria.QueryType;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author tarnschaf
 * @param <E>
 * @param <K>
 */
public abstract class GenericDAO<E, K> {

    protected Class<E> entityClass;

    @PersistenceContext(unitName = "com.ifs_KickerPlan_war_1.0-SNAPSHOTPU")
    protected EntityManager entityManager;

    @PostConstruct
    public void init() {
        this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected UaiCriteria<E> getCrit() {
        return new EasyCritImpl<>(entityManager, entityClass, QueryType.REGULAR);
    }

    public E persist(E entity) {
        return entityManager.merge(entity);
    }

    public void delete(E entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    public E findById(K id) {
        return entityManager.find(entityClass, id);
    }

    public List<E> findAll() {
        return getCrit().getResultList();

    }

    public void flush() {
        entityManager.flush();
    }

    public long count() {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(entityClass)));
        return entityManager.createQuery(cq).getSingleResult();
    }

    public void refresh(E entity) {
        entityManager.refresh(entity);
    }

    public void clear() {
        entityManager.clear();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public E findByIdentifier(String identifier) {
        UaiCriteria<E> crit = getCrit();
        crit.andEquals("identifier", identifier);
        return crit.getSingleResult();

    }
}
