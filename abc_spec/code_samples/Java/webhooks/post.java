// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.webhooks.WebhookRequest;
import com.checkout.webhooks.WebhookResponse;

CheckoutApi api = CheckoutSdk.defaultSdk()
    .staticKeys()
    .publicKey("public_key")
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

List<String> eventTypes = Arrays.asList("payment_captured", "payment_approved", "payment_declined");

WebhookRequest webhookRequest = WebhookRequest.builder()
    .url("https://docs.checkout.com/webhook")
    .eventTypes(eventTypes)
    .build();

try {
    WebhookResponse response = api.webhooksClient().registerWebhook(webhookRequest).get();
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
