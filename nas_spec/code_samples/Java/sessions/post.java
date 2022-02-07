// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.ChallengeIndicator;
import com.checkout.common.CountryCode;
import com.checkout.common.Currency;
import com.checkout.four.CheckoutApi;
import com.checkout.sessions.AuthenticationType;
import com.checkout.sessions.Category;
import com.checkout.sessions.SessionAddress;
import com.checkout.sessions.SessionRequest;
import com.checkout.sessions.SessionResponse;
import com.checkout.sessions.TransactionType;
import com.checkout.sessions.completion.HostedCompletionInfo;
import com.checkout.sessions.source.SessionCardSource;

CheckoutApi api = CheckoutSdk.fourSdk()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .scopes(FourOAuthScope.SESSIONS, FourOAuthScope.SESSIONS_APP, FourOAuthScope.SESSIONS_BROWSER) // more scopes available
    .build();

SessionRequest sessionRequest = SessionRequest.builder()
    .source(SessionCardSource.builder()
        .expiryMonth(10)
        .expiryYear(2027)
        .number("12345678")
        .build())
    .amount(10L)
    .currency(Currency.USD)
    .processingChannelId("processing_channel_id")
    .authenticationType(AuthenticationType.REGULAR)
    .authenticationCategory(Category.PAYMENT)
    .challengeIndicator(ChallengeIndicator.NO_PREFERENCE)
    .reference("reference")
    .transactionType(TransactionType.GOODS_SERVICE)
    .shippingAddress(SessionAddress.builderSessionAddress()
        .addressLine1("Checkout.com")
        .addressLine2("90 Tottenham Court Road")
        .city("London")
        .state("ENG")
        .country(CountryCode.GB)
        .zip("W1T 4TJ")
        .build())
    .completion(HostedCompletionInfo.builder()
        .successUrl("https://docs.checkout.com/sessions/success")
        .failureUrl("https://docs.checkout.com/sessions/fail")
        .build())
    .build();

try {
    SessionResponse response = api.sessionsClient().requestSession(sessionRequest).get();
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
