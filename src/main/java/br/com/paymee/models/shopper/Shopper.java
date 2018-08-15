package br.com.paymee.models.shopper;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Shopper implements Serializable {

    public Shopper() {

    }

    private String id;

    @NotNull
    @Length(max = 255)
    private String name;

    @Email
    @NotNull
    @Length(max = 50)
    private String email;

    @Valid
    @NotNull
    private ShopperDocument document;

    @Valid
    @NotNull
    private ShopperPhone phone;

    @Valid
    @NotNull
    private BankDetails bankDetails;
}
