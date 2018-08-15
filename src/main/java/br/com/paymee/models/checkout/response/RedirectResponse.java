package br.com.paymee.models.checkout.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class RedirectResponse extends PayMeeResponse implements Serializable {

    private String gatewayURL;
}
