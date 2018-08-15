package br.com.paymee.models.shopper;

import br.com.paymee.models.shopper.enums.PhoneType;
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
public class ShopperPhone implements Serializable {

    public ShopperPhone() {}

    @NotNull
    private PhoneType type = PhoneType.MOBILE;

    @NotNull
    @Length(max = 16)
    @Pattern(regexp = "([0-9-() +]{10,16})")
    private String number;
}
