// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.workflows.events.SubjectEventsResponse;
import com.checkout.workflows.reflow.ReflowByEventsRequest;

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
    .scopes(OAuthScope.FLOW_EVENTS) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

ReflowByEventsRequest request = ReflowByEventsRequest.builder()
    .events(Arrays.asList("event_id_1", "event_id_2"))
    .workflows(Arrays.asList("workflow_id_1", "workflow_id_2"))
    .build();

try {
    SubjectEventsResponse response = api.workflowsClient().getSubjectEvents("subject_id").get();
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
