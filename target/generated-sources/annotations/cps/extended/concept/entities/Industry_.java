package cps.extended.concept.entities;

import cps.extended.concept.entities.Cpe;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-20T22:46:24")
@StaticMetamodel(Industry.class)
public class Industry_ { 

    public static volatile SingularAttribute<Industry, String> industry;
    public static volatile SingularAttribute<Industry, Integer> id;
    public static volatile ListAttribute<Industry, Cpe> cpelist;

}