package br.com.paymee.models.checkout.response;

import br.com.paymee.models.checkout.request.enums.Currency;
import br.com.paymee.models.checkout.response.instructions.Instructions;
import br.com.paymee.models.shopper.Shopper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayMeeResponse implements Serializable {

    private String referenceCode;

    private Currency currency;

    private BigDecimal amount;

    private String saleCode;

    private String uuid;

    private String gatewayURL;

    private Shopper shopper;

    private Instructions instructions;
}
