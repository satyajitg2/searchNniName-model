package au.gov.ato.abrs.model.nninamesearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import uri.v3_external_search_nni_name_asic_gov.SearchNniNameReplyType;

// @Author: Johnathan Ingram (johnathan.ingram@ato.gov.au)
@Getter
@Setter
public class NniNameSearchResult {
    @JsonProperty("organisation")
    private List<Organisation> organisations;

    public NniNameSearchResult() {
    }

    public NniNameSearchResult(SearchNniNameReplyType searchResult) {
        organisations = searchResult.getBusinessDocumentBody().getOrganisation().stream()
            .map(e -> new Organisation(e))
            .collect(Collectors.toList());        
    }
}