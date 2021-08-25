package au.gov.ato.abrs.model.nninamesearch;

import au.gov.ato.abrs.model.TechArchHeader;
import lombok.Setter;
import lombok.Getter;
import uri.nni_types_asic_gov.NniTypeCodeType;
import uri.nni_types_asic_gov.SearchStatusType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/***
 * Holds the Uri parameters sent to the REST endpoint
 */
@Getter
@Setter
public class NniNameSearchUriParams extends TechArchHeader {

    @Pattern(regexp = "^[ES]$", message = "type must be either 'E' or 'S' defaults to 'E'")
    private String type = "E";
    @Pattern(regexp = "^[123ABCEGHIJLMNOPRSTX]$", message="Invalid scope defined")
    private String scope;

    @Pattern(regexp = "^[a-zA-Z0-9 .,?!(){}:;'\"@#$%*=&|\\-_\\\\/]*$", message = "organisation name invalid'")
    private String orgName;

    private NniTypeCodeType orgType;
    private SearchStatusType orgStatus;

    @Max(value = 99, message = "A maximum of 99 records can only be returned.")
    private   int maxResults;


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
        return this.orgName.toUpperCase();
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public NniTypeCodeType getOrgType() {
        return this.orgType;
    }

    public void setOrgType(NniTypeCodeType orgType) {
        this.orgType = orgType;
    }

    public SearchStatusType getOrgStatus() {
        return this.orgStatus;
    }

    public void setOrgStatus(SearchStatusType orgStatus) {
        this.orgStatus = orgStatus;
    }

    public int getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

}
