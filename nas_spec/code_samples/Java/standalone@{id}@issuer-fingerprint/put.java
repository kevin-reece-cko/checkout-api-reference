// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.sessions.GetSessionResponseAfterChannelDataSupplied;
import com.checkout.sessions.ThreeDsMethodCompletionRequest;
import com.checkout.sessions.channel.ThreeDsMethodCompletion;

CheckoutApi api = CheckoutSdk.builder()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .scopes(OAuthScope.SESSIONS_APP, OAuthScope.SESSIONS_BROWSER) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

ThreeDsMethodCompletionRequest threeDsMethodCompletionRequest = ThreeDsMethodCompletionRequest.builder()
    .threeDsMethodCompletion(ThreeDsMethodCompletion.Y)
    .build();

try {
    GetSessionResponseAfterChannelDataSupplied response = api.sessionsClient().update3dsMethodCompletionIndicator("session_id", threeDsMethodCompletionRequest).get();
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
