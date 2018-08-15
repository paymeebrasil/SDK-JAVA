package br.com.paymee.models.checkout.request;

import br.com.paymee.models.checkout.request.enums.Currency;
import br.com.paymee.models.shopper.Shopper;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class CheckoutRequest implements Serializable {

    @NotNull
    private Currency currency = Currency.BRL;

    @NotNull
    @DecimalMax("0.01")
    private BigDecimal amount;

    @NotNull
    @Length(max = 50)
    private String referenceCode;

    @Min(5)
    @Max(43200)
    @NotNull
    private Integer maxAge = 1440;

    @URL
    @NotNull
    @Length(max = 255)
    private String callbackURL;

    @Valid
    @NotNull
    private Shopper shopper;

    @Length(max = 255)
    private String observation;
}
