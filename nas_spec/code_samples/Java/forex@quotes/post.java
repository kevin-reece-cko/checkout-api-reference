// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.common.Currency;
import com.checkout.forex.QuoteRequest;
import com.checkout.forex.QuoteResponse;

// OAuth
CheckoutApi api = CheckoutSdk.builder()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .scopes(OAuthScope.FX) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

QuoteRequest request = QuoteRequest.builder()
    .sourceCurrency(Currency.GBP)
    .sourceAmount(30000L)
    .destinationCurrency(Currency.USD)
    .processChannelId("process_channel_id")
    .build();

try {
    QuoteResponse response = api.forexClient().requestQuote(request).get();
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
