// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.common.AccountHolder;
import com.checkout.common.Address;
import com.checkout.common.CountryCode;
import com.checkout.common.Phone;
import com.checkout.common.UpdateCustomerRequest;
import com.checkout.instruments.update.UpdateInstrumentCardRequest;
import com.checkout.instruments.update.UpdateInstrumentResponse;

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
    .scopes(OAuthScope.GATEWAY) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

UpdateInstrumentCardRequest updateRequest = UpdateInstrumentCardRequest.builder()
    .expiryMonth(10)
    .expiryYear(2027)
    .name("name")
    .customer(UpdateCustomerRequest.builder().id("customer_id").defaultCustomer(true).build())
    .accountHolder(AccountHolder.builder()
        .firstName("FirstName")
        .lastName("LastName")
        .phone(Phone.builder().countryCode("+1").number("415 555 2671").build())
        .billingAddress(Address.builder()
            .addressLine1("CheckoutSdk.com")
            .addressLine2("90 Tottenham Court Road")
            .city("London")
            .state("London")
            .zip("W1T 4TJ")
            .country(CountryCode.GB)
            .build())
        .build())
    .build();

try {
    UpdateInstrumentResponse response = api.instrumentsClient().update("instrument_id", updateRequest).get();
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
