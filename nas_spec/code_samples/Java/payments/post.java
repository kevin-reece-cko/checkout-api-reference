// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.Address;
import com.checkout.common.ChallengeIndicator;
import com.checkout.common.CountryCode;
import com.checkout.common.Currency;
import com.checkout.four.CheckoutApi;
import com.checkout.payments.ThreeDSRequest;
import com.checkout.payments.four.request.PaymentRequest;
import com.checkout.payments.four.request.source.RequestCardSource;
import com.checkout.payments.four.response.PaymentResponse;
import com.checkout.payments.four.sender.PaymentIndividualSender;
import com.checkout.payments.four.sender.SenderIdentification;
import com.checkout.payments.four.sender.SenderIdentificationType;

// API Keys
CheckoutApi api = CheckoutSdk.fourSdk()
    .staticKeys()
    .publicKey("public_key")
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

// OAuth
CheckoutApi api = CheckoutSdk.fourSdk()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .scopes(FourOAuthScope.GATEWAY) // more scopes available
    .build();

RequestCardSource source = RequestCardSource.builder()
    .number("123456789")
    .expiryMonth(10)
    .expiryYear(2027)
    .cvv("123")
    .stored(false)
    .build();

PaymentIndividualSender sender = PaymentIndividualSender.builder()
    .firstName("FirstName")
    .lastName("LastName")
    .address(Address.builder()
        .addressLine1("Checkout")
        .addressLine2("90 Tottenham Court Road")
        .city("London")
        .state("London")
        .zip("W1T 4TJ")
        .country(CountryCode.GB)
        .build())
    .identification(SenderIdentification.builder()
        .type(SenderIdentificationType.DRIVING_LICENCE)
        .number("1234")
        .issuingCountry(CountryCode.GB)
        .build())
    .build();

ThreeDSRequest threeDSRequest = ThreeDSRequest.builder()
    .enabled(true)
    .challengeIndicator(ChallengeIndicator.NO_CHALLENGE_REQUESTED)
    .build();

PaymentRequest request = PaymentRequest.builder().source(source).sender(sender)
    .capture(false)
    .reference("reference")
    .amount(10L)
    .currency(Currency.GBP)
    .threeDS(threeDSRequest)
    .successUrl("https://docs.checkout.com/success")
    .failureUrl("https://docs.checkout.com/failure")
    .build();

try {
    PaymentResponse response = api.paymentsClient().requestPayment(request).get();
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
