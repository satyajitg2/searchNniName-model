package au.gov.ato.abrs.model.nninamesearch;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import uri.nni_types_asic_gov.IdentifierType;
import uri.nni_types_asic_gov.NameIndexAcncEntityType;
import uri.nni_types_asic_gov.NniTypeType;
import uri.bn_types_asic_gov.PreviousStateTerritoryType;
import uri.nni_types_asic_gov.ClassType;
import uri.types_asic_gov.DistinguishedNameType;
import uri.types_asic_gov.StateTerritoryCodeType;
import uri.types_asic_gov.StatusType;

// @Author: Johnathan Ingram (johnathan.ingram@ato.gov.au)
@Getter
@Setter
public class Organisation {
    private IdentifierType identifier = new IdentifierType();
    
    private DistinguishedNameType name = new DistinguishedNameType();
    
    private NniTypeType type;
    
    @JsonProperty("class")
    private ClassType clazz = new ClassType();

    private ClassType subclass = new ClassType();

    private StatusType status = new StatusType();

    private StateTerritoryCodeType incorporationState;      // ST-0433

    private String placeOfIncorporation;                    // ST-0434

    private PreviousStateTerritoryType prevStateTerritory = new PreviousStateTerritoryType();

    private Boolean acnFlag;                                // ST-0437

    public Organisation() {
    }

    public Organisation(NameIndexAcncEntityType org) {
        this.identifier = org.getIdentifier();
        this.name = org.getName();
        this.type = org.getType();
        this.clazz = org.getClazz();
        this.subclass = org.getSubClass();
        this.status = org.getStatus();
        this.incorporationState = org.getIncorporationState();
        this.placeOfIncorporation = org.getPlaceOfIncorporation();
        this.prevStateTerritory = org.getPreviousStateTerritory();
        this.acnFlag = org.isAcncFlag();        
    }

}
