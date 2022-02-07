// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.events.EventsPageResponse;
import com.checkout.events.RetrieveEventsRequest;

CheckoutApi api = CheckoutSdk.defaultSdk()
    .staticKeys()
    .publicKey("public_key")
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

RetrieveEventsRequest retrieveEventsRequest = RetrieveEventsRequest.builder()
    .from(LocalDateTime.now().minusYears(2).toInstant(ZoneOffset.UTC))
    .to(LocalDateTime.now().toInstant(ZoneOffset.UTC))
    .limit(15)
    .skip(0)
    //.paymentId("payment_id")
    .build();

try {
    EventsPageResponse response = api.eventsClient().retrieveEvents(retrieveEventsRequest).get();
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
