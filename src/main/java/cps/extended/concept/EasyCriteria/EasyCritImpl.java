package cps.extended.concept.EasyCriteria;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.uaihebert.uaicriteria.UaiCriteriaImp;
import com.uaihebert.uaicriteria.criteria.QueryType;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class EasyCritImpl<T> extends UaiCriteriaImp<T> {

    public EasyCritImpl(EntityManager entityManager, Class<T> entityClass, QueryType queryType) {
        super(entityManager, entityClass, queryType);
    }

    @Override
    public T getSingleResult() {
        final TypedQuery<T> query = basicCriteriaElements.getRegularQuery();
        T singleResult = null;
        try {
            singleResult = query.getSingleResult();
        } catch (Exception ex) {

        }
        return singleResult;
    }
}
