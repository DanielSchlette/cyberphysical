package cps.extended.concept.entities;

import cps.extended.concept.entities.Cpe;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T11:03:44")
@StaticMetamodel(Communication.class)
public class Communication_ { 

    public static volatile ListAttribute<Communication, Cpe> communicationcpes;
    public static volatile SingularAttribute<Communication, Integer> id;
    public static volatile SingularAttribute<Communication, String> communication;

}