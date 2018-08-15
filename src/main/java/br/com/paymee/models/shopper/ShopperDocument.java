package br.com.paymee.models.shopper;

import br.com.paymee.models.shopper.enums.DocumentType;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class ShopperDocument implements Serializable {

    public ShopperDocument() {}

    @NotNull
    private DocumentType type;

    @NotNull
    @Length(max = 18)
    @Pattern(regexp = "([0-9./\\-]{11,18})")
    private String number;
}
