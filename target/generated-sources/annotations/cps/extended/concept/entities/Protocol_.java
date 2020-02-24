package cps.extended.concept.entities;

import cps.extended.concept.entities.Cpe;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-24T13:25:05")
@StaticMetamodel(Protocol.class)
public class Protocol_ { 

    public static volatile SingularAttribute<Protocol, String> protocol;
    public static volatile SingularAttribute<Protocol, Integer> id;
    public static volatile ListAttribute<Protocol, Cpe> cpelist;

}