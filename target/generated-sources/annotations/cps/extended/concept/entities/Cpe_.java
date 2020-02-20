package cps.extended.concept.entities;

import cps.extended.concept.entities.CPSBundle;
import cps.extended.concept.entities.Cve;
import cps.extended.concept.entities.Part;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-20T20:53:27")
@StaticMetamodel(Cpe.class)
public class Cpe_ { 

    public static volatile SingularAttribute<Cpe, String> product;
    public static volatile SingularAttribute<Cpe, String> other;
    public static volatile SingularAttribute<Cpe, String> softwareEdition;
    public static volatile SingularAttribute<Cpe, String> targetSoftware;
    public static volatile SingularAttribute<Cpe, Part> part;
    public static volatile SingularAttribute<Cpe, String> update;
    public static volatile SingularAttribute<Cpe, String> edition;
    public static volatile SingularAttribute<Cpe, String> language;
    public static volatile SingularAttribute<Cpe, String> targetHardware;
    public static volatile SingularAttribute<Cpe, String> title;
    public static volatile SingularAttribute<Cpe, String> version;
    public static volatile SingularAttribute<Cpe, String> cpe23String;
    public static volatile SingularAttribute<Cpe, String> vendor;
    public static volatile SingularAttribute<Cpe, String> cpe22String;
    public static volatile ListAttribute<Cpe, CPSBundle> bundles;
    public static volatile SingularAttribute<Cpe, Long> id;
    public static volatile ListAttribute<Cpe, Cve> cyberCves;

}