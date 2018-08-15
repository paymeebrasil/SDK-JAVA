package br.com.paymee.models.checkout.response.instructions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Instructions implements Serializable {

    private String chosen;

    private String name;

    private String label;

    @JsonProperty("beneficiary_branch")
    private String beneficiaryBranch;

    @JsonProperty("beneficiary_account")
    private String beneficiaryAccount;

    @JsonProperty("beneficiary_name")
    private String beneficiaryName;

    @JsonProperty("need_identification")
    private boolean mandatoryIdentification;

    private String identification;

    private List<String> steps;

    @JsonProperty("redirect_urls")
    private InstructionsUrls redirectUrls;
}
