package cps.extended.concept.entities;

import cps.extended.concept.entities.Cpe;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-20T22:46:24")
@StaticMetamodel(Cve.class)
public class Cve_ { 

    public static volatile SingularAttribute<Cve, String> date;
    public static volatile ListAttribute<Cve, String> urls;
    public static volatile ListAttribute<Cve, Cpe> cyberCpes;
    public static volatile SingularAttribute<Cve, String> cveId;
    public static volatile SingularAttribute<Cve, String> description;
    public static volatile SingularAttribute<Cve, Long> id;
    public static volatile SingularAttribute<Cve, String> source;

}