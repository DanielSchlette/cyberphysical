package cps.extended.concept.entities;

import cps.extended.concept.entities.Cpe;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-20T22:16:27")
@StaticMetamodel(Capability.class)
public class Capability_ { 

    public static volatile SingularAttribute<Capability, String> capability;
    public static volatile SingularAttribute<Capability, Integer> id;
    public static volatile ListAttribute<Capability, Cpe> cpelist;

}