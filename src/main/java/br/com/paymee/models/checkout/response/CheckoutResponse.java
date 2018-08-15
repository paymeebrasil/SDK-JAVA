package br.com.paymee.models.checkout.response;

import br.com.paymee.models.checkout.response.errors.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckoutResponse implements Serializable {

    private Integer status;

    private String message;

    private Integer errorCount;

    private List<ErrorResponse> errors;

    private PayMeeResponse response;
}
