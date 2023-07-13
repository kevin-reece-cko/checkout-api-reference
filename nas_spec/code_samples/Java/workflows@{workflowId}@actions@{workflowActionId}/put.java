// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.EmptyResponse;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.workflows.actions.WebhookSignature;
import com.checkout.workflows.actions.request.WebhookWorkflowActionRequest;

// API Keys
CheckoutApi api = CheckoutSdk.builder()
    .staticKeys()
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

// OAuth
CheckoutApi api = CheckoutSdk.builder()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .scopes(OAuthScope.FLOW_WORKFLOWS) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

WebhookWorkflowActionRequest request = WebhookWorkflowActionRequest.builder()
    .signature(WebhookSignature.builder().key("8V8x0dLK%AyD*DNS8JJr").method("HMACSHA256").build())
    .headers(new HashMap<>())
    .url("https://docs.checkout.com/fail")
    .build();

try {
    EmptyResponse response = api.workflowsClient().updateWorkflowAction("workflow_id", "action_id", request).get();
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
