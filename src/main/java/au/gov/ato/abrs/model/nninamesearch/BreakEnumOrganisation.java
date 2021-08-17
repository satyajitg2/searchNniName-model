package au.gov.ato.abrs.model.nninamesearch;

import au.gov.ato.abrs.model.TechArchHeader;
import lombok.Getter;
import lombok.Setter;
import uri.nni_types_asic_gov.NniTypeCodeType;
import uri.nni_types_asic_gov.SearchStatusType;

@Getter
@Setter
public class BreakEnumOrganisation extends TechArchHeader {
    private String orgName;
    private NniTypeCodeType orgType;
    private SearchStatusType orgStatus;
}
