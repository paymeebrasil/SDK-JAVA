package br.com.paymee;

import br.com.paymee.helpers.PayMeeCheckout;
import br.com.paymee.models.PayMeeEnviroment;
import br.com.paymee.models.checkout.request.CheckoutTransparentRequest;
import br.com.paymee.models.checkout.request.enums.Currency;
import br.com.paymee.models.checkout.request.enums.PaymentMethod;
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
 * Minimal transparent request usage
 * @author Joao Moreno
 */
public class TransparentCheckoutExample {

    public static void main(String ... args) {

        //Create new instance of PayMeeCheckout
        PayMeeCheckout payMeeCheckout = new PayMeeCheckout(
                "your-x-api-key",
                "your-x-api-token",
                PayMeeEnviroment.SANDBOX);

        //Populate checkoutRequest object
        CheckoutTransparentRequest checkoutRequest = new CheckoutTransparentRequest();
        checkoutRequest.setCurrency(Currency.BRL);
        checkoutRequest.setAmount(new BigDecimal("155.99"));
        checkoutRequest.setReferenceCode("0230020032");
        checkoutRequest.setMaxAge(1440);
        checkoutRequest.setCallbackURL("https://foo.bar/paymeeListener");
        checkoutRequest.setPaymentMethod(PaymentMethod.ITAU_TRANSFER_GENERIC);
        checkoutRequest.setShopper(Shopper.builder()
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
        CheckoutResponse response = payMeeCheckout.create(checkoutRequest);

        //output success response
        if(response.getStatus().equals(0)) {

            PayMeeResponse successResponse = response.getResponse();

            System.out.println("TRANSACTION UUID: " + successResponse.getUuid());
            System.out.println("TRANSACTION AMOUNT: " + successResponse.getAmount());
            System.out.println("TRANSACTION REFERENCE CODE: " + successResponse.getReferenceCode());
            System.out.println();

            System.out.println("BENEFICIARY NAME: " + successResponse.getInstructions().getBeneficiaryName());
            System.out.println("BENEFICIARY BRANCH: " + successResponse.getInstructions().getBeneficiaryBranch());
            System.out.println("BENEFICIARY ACCOUNT: " + successResponse.getInstructions().getBeneficiaryAccount());
            System.out.println();

            if(successResponse.getInstructions().isMandatoryIdentification()) {
                System.out.println("IDENTIFICATION CODE: " + successResponse.getInstructions().getIdentification());
                System.out.println();
            }

            System.out.println("INSTRUCTIONS: ");
            for(String step: response.getResponse().getInstructions().getSteps()) {
                System.out.println(step);
            }

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
