package cps.extended.concept.entities;

import cps.extended.concept.entities.Cpe;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-24T14:33:05")
@StaticMetamodel(ProgrammingLang.class)
public class ProgrammingLang_ { 

    public static volatile SingularAttribute<ProgrammingLang, String> language;
    public static volatile SingularAttribute<ProgrammingLang, Integer> id;
    public static volatile ListAttribute<ProgrammingLang, Cpe> cpelist;

}