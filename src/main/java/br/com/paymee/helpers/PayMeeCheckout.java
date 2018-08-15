package br.com.paymee.helpers;

import br.com.paymee.helpers.converters.BeanConverter;
import br.com.paymee.models.checkout.request.CheckoutTransparentRequest;
import br.com.paymee.models.PayMeeEnviroment;
import br.com.paymee.models.checkout.request.CheckoutRequest;
import br.com.paymee.models.checkout.response.CheckoutResponse;
import lombok.NonNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Joao Moreno
 */
public class PayMeeCheckout {

    private static final String API_VERSION = "v1.1";
    private final String apiKey;
    private final String apiToken;
    private final PayMeeEnviroment enviroment;

    /**
     * @param apiKey if you don't have an api-key, please visit https://apisandbox.paymee.com.br/register
     * @param apiToken if you don't have an api-token, please visit https://apisandbox.paymee.com.br/register
     * @param enviroment define your current environment (SANDBOX or PRODUCTION)
     */
    public PayMeeCheckout(@NonNull final String apiKey,
                          @NonNull final String apiToken,
                          PayMeeEnviroment enviroment) {

        this.apiKey = apiKey;
        this.apiToken = apiToken;
        this.enviroment = (enviroment == null) ? PayMeeEnviroment.PRODUCTION : enviroment;
    }

    /**
     * @param checkoutRequest is the checkout object
     * @return API response
     */
    public CheckoutResponse create(final CheckoutRequest checkoutRequest) {

        String API_URL = ".paymee.com.br/" + API_VERSION;
        final String checkoutURL = "https://" + ((this.enviroment.equals(PayMeeEnviroment.PRODUCTION)) ? "api" : "apisandbox") + API_URL + "/checkout" +
                ((checkoutRequest instanceof CheckoutTransparentRequest) ? "/transparent" : "");

        System.out.println(checkoutURL);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("x-api-key", this.apiKey);
        headers.set("x-api-token", this.apiToken);

        HttpEntity<CheckoutRequest> httpEntity = new HttpEntity<>(checkoutRequest, headers);

        try {
            ResponseEntity<CheckoutResponse> entity = restTemplate.postForEntity(checkoutURL,
                    httpEntity, CheckoutResponse.class);
            return entity.getBody();
        } catch (HttpClientErrorException e) {
            return BeanConverter.fromJSON(e.getResponseBodyAsString(), CheckoutResponse.class);
        }
    }
}
