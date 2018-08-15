package br.com.paymee.models.shopper;

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
public class BankDetails implements Serializable {

    public BankDetails() {}

    @NotNull
    @Length(max = 4)
    @Pattern(regexp = "([0-9]{1,4})")
    private String branch;

    @NotNull
    @Length(max = 12)
    @Pattern(regexp = "([0-9]{1,11})-([0-9Xx]{1})")
    private String account;
}
