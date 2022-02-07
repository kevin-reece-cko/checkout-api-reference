// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.disputes.DisputeDetailsResponse;

CheckoutApi api = CheckoutSdk.defaultSdk()
    .staticKeys()
    .publicKey("public_key")
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

try {
    DisputeDetailsResponse response = api.disputesClient().getDisputeDetails("dispute_id").get();
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
