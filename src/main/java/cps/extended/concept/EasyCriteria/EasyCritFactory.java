package cps.extended.concept.EasyCriteria;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.criteria.QueryType;
import javax.persistence.EntityManager;

public class EasyCritFactory {

    public static <T> UaiCriteria<T> createQueryCriteria(final EntityManager entityManager, final Class<T> classToUse) {
        return new EasyCritImpl<>(entityManager, classToUse, QueryType.REGULAR);
    }
}
