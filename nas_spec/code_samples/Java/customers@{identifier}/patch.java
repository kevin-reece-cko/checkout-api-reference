// For more information please refer to https://github.com/checkout/checkout-sdk-java


import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.Phone;
import com.checkout.customers.four.CustomerRequest;
import com.checkout.four.CheckoutApi;

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
    .scopes(FourOAuthScope.GATEWAY) // more scopes available
    .build();

CustomerRequest customerRequest = CustomerRequest.builder()
    .email("email@docs.checkout.com")
    .name("name")
    .phone(Phone.builder().countryCode("1").number("4155552671").build())
    .instruments(Arrays.asList("instrument_id_1", "instrument_id_2"))
    .metadata(new HashMap<>())
    .build();

try {
    api.customersClient().update("customer_id", customerRequest).get();
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
