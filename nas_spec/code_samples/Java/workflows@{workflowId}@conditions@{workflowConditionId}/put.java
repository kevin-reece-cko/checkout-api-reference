// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.EmptyResponse;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.workflows.conditions.request.EventWorkflowConditionRequest;

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

EventWorkflowConditionRequest request = EventWorkflowConditionRequest.builder()
    .events(new HashMap<>())
    .build();

try {
    EmptyResponse response = api.workflowsClient().updateWorkflowCondition("workflow_id", "condition_id", request).get();
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
