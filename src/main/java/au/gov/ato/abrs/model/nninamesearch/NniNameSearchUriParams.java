package au.gov.ato.abrs.model.nninamesearch;

import lombok.Setter;
import lombok.Getter;
import uri.nni_types_asic_gov.SearchStatusType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

/***
 * Holds the Uri parameters sent to the REST endpoint
 */
@Getter
@Setter
public class NniNameSearchUriParams  {

    @Pattern(regexp = "^[ES]$", message = "type must be either 'E' or 'S' defaults to 'E'")
    private String type = "E";
    @Pattern(regexp = "^[123ABCEGHIJLMNOPRSTX]$", message="Invalid scope defined")
    private String scope;

    private String orgName;
    //private NniTypeCodeType orgType;
    private SearchStatusType orgStatus;

    private BreakEnumOrganisation organisation;

    @Max(value = 99, message = "A maximum of 99 records can only be returned.")
    private   int maxResults;
}
