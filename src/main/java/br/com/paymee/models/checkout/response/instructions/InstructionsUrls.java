package br.com.paymee.models.checkout.response.instructions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class InstructionsUrls implements Serializable {

    private String desktop;

    private String ios;

    private String android;

    @JsonProperty("windows_phone")
    private String windowsPhone;
}
