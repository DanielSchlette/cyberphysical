package cps.extended.concept.entities.asset;

import cps.extended.concept.entities.vulnerability.Cve;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-17T22:27:42")
@StaticMetamodel(Cpe.class)
public class Cpe_ extends AbstractCpe_ {

    public static volatile SingularAttribute<Cpe, String> cpe22String;
    public static volatile SingularAttribute<Cpe, String> part;
    public static volatile ListAttribute<Cpe, Cve> cyberCves;
    public static volatile SingularAttribute<Cpe, String> cpe23String;

}