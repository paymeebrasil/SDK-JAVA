package br.com.paymee.models.checkout.response;

import br.com.paymee.models.checkout.response.instructions.Instructions;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SemiTransparentResponse extends PayMeeResponse implements Serializable {

    private Instructions instructions;
}
