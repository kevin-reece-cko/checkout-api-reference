// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.Address;
import com.checkout.common.CountryCode;
import com.checkout.common.Currency;
import com.checkout.common.Phone;
import com.checkout.payments.request.PaymentRequest;
import com.checkout.payments.request.source.RequestCardSource;
import com.checkout.payments.response.PaymentResponse;

CheckoutApi api = CheckoutSdk.defaultSdk()
    .staticKeys()
    .publicKey("public_key")
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

RequestCardSource source = RequestCardSource.builder()
    .name("name")
    .number("number")
    .expiryMonth(12)
    .expiryYear(2025)
    .cvv("123")
    .stored(false)
    .billingAddress(Address.builder()
        .addressLine1("Checkout")
        .addressLine2("90 Tottenham Court Road")
        .city("London")
        .state("London")
        .zip("W1T 4TJ")
        .country(CountryCode.GB)
        .build())
    .phone(Phone.builder().countryCode("1").number("415 555 2671").build())
    .build();

PaymentRequest paymentRequest = PaymentRequest.builder()
    .source(source)
    .capture(true)
    .reference("reference")
    .amount(10L)
    .currency(Currency.GBP)
    .build();

try {
    PaymentResponse response = api.paymentsClient().requestPayment(paymentRequest).get(); // or "requestPayout"
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
