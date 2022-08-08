// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.ItemsResponse;
import com.checkout.events.previous.EventTypes;
import com.checkout.previous.CheckoutApi;

CheckoutApi api = CheckoutSdk
    .builder()
    .previous()
    .staticKeys()
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

try {
    /*
       Specify API version:
        "1.0" => Legacy API
        "2.0" => Unified Payments API
        null  => all versions
    */
    ItemsResponse<EventTypes> response = api.eventsClient().retrieveAllEventTypes(null).get();
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
