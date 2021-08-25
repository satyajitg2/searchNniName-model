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

    @Pattern(regexp = "^[A-Z0-9 .,?!(){}:;'\"@#$%*=&|\\-_\\\\/]*$", message = "organisation name invalid'")
    private String orgName;
    private NniTypeCodeType orgType;
    private SearchStatusType orgStatus;

    @Max(value = 99, message = "A maximum of 99 records can only be returned.")
    private   int maxResults;
}
