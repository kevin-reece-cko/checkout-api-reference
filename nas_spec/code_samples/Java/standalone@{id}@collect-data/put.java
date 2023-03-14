// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.sessions.GetSessionResponse;
import com.checkout.sessions.channel.BrowserSession;
import com.checkout.sessions.channel.ChannelData;
import com.checkout.sessions.channel.ThreeDsMethodCompletion;

CheckoutApi api = CheckoutSdk.builder()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .scopes(OAuthScope.SESSIONS_APP, OAuthScope.SESSIONS_BROWSER) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

ChannelData channelData = BrowserSession.builder() // other channel data types available
    .acceptHeader("Accept:  *.*, q=0.1")
    .javaEnabled(true)
    .language("FR-fr")
    .colorDepth("16")
    .screenWidth("1920")
    .screenHeight("1080")
    .timezone("60")
    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36")
    .threeDsMethodCompletion(ThreeDsMethodCompletion.Y)
    .ipAddress("1.12.123.255")
    .build();

try {
    GetSessionResponse response = api.sessionsClient().updateSession("session_id", channelData).get();
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

