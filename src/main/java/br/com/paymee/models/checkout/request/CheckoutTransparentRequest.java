package br.com.paymee.models.checkout.request;

import br.com.paymee.models.checkout.request.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class CheckoutTransparentRequest extends CheckoutRequest implements Serializable {

    @NotNull
    private PaymentMethod paymentMethod;
}
