package br.com.paymee.models.checkout.response.errors;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ErrorResponse implements Serializable {

    private String field;

    private String message;
}
