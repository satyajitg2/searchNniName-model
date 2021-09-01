package au.gov.ato.abrs.model.nninamesearch;

import au.gov.ato.abrs.model.TechArchHeader;
import lombok.Setter;
import lombok.Getter;
import uri.nni_types_asic_gov.NniTypeCodeType;
import uri.nni_types_asic_gov.SearchStatusType;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class NniNameSearchUriParams extends TechArchHeader {

    @NotNull(message="Missing search type")
    @Pattern(regexp = "^[ES]$", message = "Invalid search type")
    private String searchType = "E";

    @NotNull(message="Missing search scope")
    @Pattern(regexp = "^[123ABCEGHIJLMNOPRSTX]$", message = "Invalid search scope")
    private String searchScope = "A";

    @NotNull(message="Missing organisation name")
    @Pattern(regexp = "^[a-zA-Z0-9 .,?!(){}:;'\"@#$%*=&|\\-_\\\\/]*$", message = "Invalid organisation name")
    private String organisationName;

    @Pattern(regexp = "^(APTY|APUB|ASSN|BUSN|CHAR|COMP|COOP|FNOS|LTDP|MISM|NONC|NRET|RACN|REBD|RSVN|SOLS|TRST)$", message="Invalid organisation type")
    private String organisationType;

    @Pattern(regexp = "^(REGD|DRGD|PEND)$", message="Ivalid organisation status")
    private String organisationStatus;

    @Max(value = 99, message = "Invalid max results")
    private int maxResults = 99;

    public String getOrganisationName() {
        return null == this.organisationName ? null : this.organisationName.toUpperCase();
    }

    public NniTypeCodeType getOrganisationType() {
        return null == organisationType ? null : NniTypeCodeType.valueOf(organisationType);
    }

    public SearchStatusType getOrganisationStatus() {
        return null == organisationStatus ? null : SearchStatusType.valueOf(organisationStatus);
    }
}
