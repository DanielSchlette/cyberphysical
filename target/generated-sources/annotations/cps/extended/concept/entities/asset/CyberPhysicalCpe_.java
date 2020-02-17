package cps.extended.concept.entities.asset;

import cps.extended.concept.entities.asset.attributes.Capability;
import cps.extended.concept.entities.asset.attributes.Communication;
import cps.extended.concept.entities.asset.attributes.Industry;
import cps.extended.concept.entities.asset.attributes.Part;
import cps.extended.concept.entities.asset.attributes.Protocol;
import cps.extended.concept.entities.vulnerability.Cve;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-17T22:22:19")
@StaticMetamodel(CyberPhysicalCpe.class)
public class CyberPhysicalCpe_ extends AbstractCpe_ {

    public static volatile ListAttribute<CyberPhysicalCpe, Capability> capabilities;
    public static volatile SingularAttribute<CyberPhysicalCpe, Part> part;
    public static volatile ListAttribute<CyberPhysicalCpe, Industry> industries;
    public static volatile ListAttribute<CyberPhysicalCpe, Cve> cyberPhysicalCves;
    public static volatile SingularAttribute<CyberPhysicalCpe, Communication> communication;
    public static volatile ListAttribute<CyberPhysicalCpe, Protocol> protocols;

}