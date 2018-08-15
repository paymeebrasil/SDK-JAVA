package br.com.paymee;

import br.com.paymee.helpers.PayMeeCheckout;
import br.com.paymee.models.PayMeeEnviroment;
import br.com.paymee.models.checkout.request.CheckoutRequest;
import br.com.paymee.models.checkout.request.enums.Currency;
import br.com.paymee.models.checkout.response.CheckoutResponse;
import br.com.paymee.models.checkout.response.errors.ErrorResponse;
import br.com.paymee.models.shopper.BankDetails;
import br.com.paymee.models.shopper.Shopper;
import br.com.paymee.models.shopper.ShopperDocument;
import br.com.paymee.models.shopper.ShopperPhone;
import br.com.paymee.models.shopper.enums.DocumentType;
import br.com.paymee.models.shopper.enums.PhoneType;

import java.math.BigDecimal;
import java.util.List;

/**
 * Minimal redirect request usage
 * @author Joao Moreno
 */
public class RedirectCheckoutExample {

    public static void main(String ... args) {

        //Create new instance of PayMeeCheckout
        PayMeeCheckout payMeeCheckout = new PayMeeCheckout(
                "your-x-api-key",
                "your-x-api-token",
                PayMeeEnviroment.SANDBOX);

        //Populate checkoutRequest object
        CheckoutRequest redirectCheckoutRequest = new CheckoutRequest();
        redirectCheckoutRequest.setCurrency(Currency.BRL);
        redirectCheckoutRequest.setAmount(new BigDecimal("155.99"));
        redirectCheckoutRequest.setReferenceCode("0230020035");
        redirectCheckoutRequest.setMaxAge(1440);
        redirectCheckoutRequest.setCallbackURL("https://foo.bar/paymeeListener");
        redirectCheckoutRequest.setShopper(Shopper.builder()
                .name("JOHN DOE")
                .email("foo@bar.foo")
                .document(ShopperDocument.builder()
                        .type(DocumentType.CPF)
                        .number("00000000000")
                        .build())
                .phone(ShopperPhone.builder()
                        .type(PhoneType.MOBILE)
                        .number("11999900000").build())
                .bankDetails(BankDetails.builder()
                        .branch("1234")
                        .account("12345-0")
                        .build())
                .build());

        //Create checkout request
        CheckoutResponse response = payMeeCheckout.create(redirectCheckoutRequest);

        //output success response
        if(response.getStatus().equals(0)) {

            PayMeeResponse successResponse = response.getResponse();

            System.out.println("GATEWAY URL: " + successResponse.getGatewayURL());
            System.out.println();
            System.out.println("TRANSACTION UUID: " + successResponse.getUuid());
            System.out.println("TRANSACTION AMOUNT: " + successResponse.getAmount());
            System.out.println("TRANSACTION REFERENCE CODE: " + successResponse.getReferenceCode());
            System.out.println();
        }
        //output error response
        else {

            List<ErrorResponse> errorResponseList = response.getErrors();
            System.out.println("ERRORS COUNT: " + response.getErrorCount());
            System.out.println("STATUS MESSAGE: " + response.getMessage());
            System.out.println();

            System.out.println("ERRORS DESCRIPTION: ");
            System.out.println();

            for(ErrorResponse error : errorResponseList) {
                System.out.println("field=" + error.getField() + ", message=" + error.getMessage());
            }
        }
    }
}
