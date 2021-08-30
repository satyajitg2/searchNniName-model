package au.gov.ato.abrs.model.nninamesearch;

import au.gov.ato.abrs.model.TechArchHeader;
import lombok.Setter;
import lombok.Getter;
//import uri.nni_types_asic_gov.NniTypeCodeType;
//import uri.nni_types_asic_gov.SearchStatusType;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/***
 * Holds the Uri parameters sent to the REST endpoint
 */
@Getter
@Setter
public class NniNameSearchUriParams extends TechArchHeader {

    @Pattern(regexp = "^[ES]$", message = "type must be either 'E' or 'S' defaults to 'E'")
    private String type = "E";

    @NotNull(message="No scope defined")
    @Pattern(regexp = "^[123ABCEGHIJLMNOPRSTX]$", message = "Invalid scope defined")
    private String scope;

    @NotNull(message="No organisation name defined")
    @Pattern(regexp = "^[a-zA-Z0-9 .,?!(){}:;'\"@#$%*=&|\\-_\\\\/]*$", message = "Organisation name invalid'")
    private String orgName;

    @Pattern(regexp = "^(APTY|APUB|ASSN|BUSN|CHAR|COMP|COOP|FNOS|LTDP|MISM|NONC|NRET|RACN|REBD|RSVN|SOLS|TRST)$", message="Organisation type invalid")
    private String orgType;

    private String orgStatus;

    @Max(value = 99, message = "A maximum of 99 records can only be returned.")
    private int maxResults = 99;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getOrgName() {
        return null == this.orgName ? null : this.orgName.toUpperCase();
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgType() {
        return this.orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgStatus() {
        return this.orgStatus;
    }

    public void setOrgStatus(String orgStatus) {
        this.orgStatus = orgStatus;
    }

    public int getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

}
