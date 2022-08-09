// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.workflows.CreateWorkflowRequest;
import com.checkout.workflows.CreateWorkflowResponse;
import com.checkout.workflows.actions.WebhookSignature;
import com.checkout.workflows.actions.request.WebhookWorkflowActionRequest;
import com.checkout.workflows.conditions.request.EntityWorkflowConditionRequest;
import com.checkout.workflows.conditions.request.ProcessingChannelWorkflowConditionRequest;

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

WebhookWorkflowActionRequest workflowActionRequest = WebhookWorkflowActionRequest.builder()
    .url("https://docs.checkout.com/webhook")
    .headers(new HashMap<>())
    .signature(WebhookSignature.builder().key("8V8x0dLK%AyD*DNS8JJr").method("HMACSHA256").build())
    .build();

EntityWorkflowConditionRequest condition1 = EntityWorkflowConditionRequest.builder()
    .entities(Collections.singletonList("workflow_entity_id"))
    .build();

ProcessingChannelWorkflowConditionRequest condition2 = ProcessingChannelWorkflowConditionRequest.builder()
    .processingChannels(Collections.singletonList("processing_channel_id"))
    .build();

CreateWorkflowRequest request = CreateWorkflowRequest.builder()
    .name("name")
    .actions(Collections.singletonList(workflowActionRequest))
    .conditions(Arrays.asList(condition1, condition2))
    .build();

try {
    CreateWorkflowResponse response = api.workflowsClient().createWorkflow(request).get();
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
