// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.four.CheckoutApi;
import com.checkout.workflows.four.CreateWorkflowRequest;
import com.checkout.workflows.four.CreateWorkflowResponse;
import com.checkout.workflows.four.actions.WebhookSignature;
import com.checkout.workflows.four.actions.request.WebhookWorkflowActionRequest;
import com.checkout.workflows.four.conditions.request.EntityWorkflowConditionRequest;
import com.checkout.workflows.four.conditions.request.EventWorkflowConditionRequest;
import com.checkout.workflows.four.conditions.request.ProcessingChannelWorkflowConditionRequest;

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
    .scopes(FourOAuthScope.FLOW, FourOAuthScope.FLOW_WORKFLOWS, FourOAuthScope.FLOW_EVENTS) // more scopes available
    .build();

WebhookWorkflowActionRequest workflowActionRequest = WebhookWorkflowActionRequest.builder()
    .url("https://docs.checkout.com/webhook")
    .headers(new HashMap<>())
    .signature(WebhookSignature.builder().key("8V8x0dLK%AyD*DNS8JJr").method("HMACSHA256").build())
    .build();

EventWorkflowConditionRequest condition1 = EventWorkflowConditionRequest.builder()
    .events(new HashMap<>())
    .build();

EntityWorkflowConditionRequest condition2 = EntityWorkflowConditionRequest.builder()
    .entities(Collections.singletonList("workflow_entity_id"))
    .build();

ProcessingChannelWorkflowConditionRequest condition3 = ProcessingChannelWorkflowConditionRequest.builder()
    .processingChannels(Collections.singletonList("processing_channel_id"))
    .build();

CreateWorkflowRequest request = CreateWorkflowRequest.builder()
    .name("name")
    .actions(Collections.singletonList(workflowActionRequest))
    .conditions(Arrays.asList(condition1, condition2, condition3))
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
