package au.gov.ato.abrs.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TechArchHeader {
    @NotNull
    private String UserIdentity;
    @NotNull
    private String SessionId;
    @NotNull
    private   String TransactionId;
}
