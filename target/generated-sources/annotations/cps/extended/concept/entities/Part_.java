package cps.extended.concept.entities;

import cps.extended.concept.entities.Cpe;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-24T18:10:49")
@StaticMetamodel(Part.class)
public class Part_ { 

    public static volatile SingularAttribute<Part, String> part;
    public static volatile ListAttribute<Part, Cpe> partcpes;
    public static volatile SingularAttribute<Part, Integer> id;

}