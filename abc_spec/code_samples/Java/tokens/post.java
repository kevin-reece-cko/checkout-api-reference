// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.Address;
import com.checkout.common.CountryCode;
import com.checkout.tokens.CardTokenRequest;
import com.checkout.tokens.CardTokenResponse;

CheckoutApi api = CheckoutSdk.defaultSdk()
    .staticKeys()
    .publicKey("public_key")
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

CardTokenRequest request = CardTokenRequest.builder()
    .number("123456789")
    .expiryMonth(10)
    .expiryYear(2025)
    .cvv("123")
    .name("name")
    .billingAddress(Address.builder()
        .addressLine1("Checkout")
        .addressLine2("90 Tottenham Court Road")
        .city("London")
        .state("London")
        .zip("W1T 4TJ")
        .country(CountryCode.GB)
        .build())
    .build();

try {
    CardTokenResponse response = api.tokensClient().request(request).get();
} catch (CheckoutApiException e) {
    // API error
    String requestId = e.getRequestId();
    int statusCode = e.getHttpStatusCode();
    Map<String, Object> errorDetails = e.getErrorDetails();
} catch (CheckoutArgumentException e) {
    // Bad arguments
} catch (CheckoutAuthorizationException e) {
    // Invalid authorization
}
