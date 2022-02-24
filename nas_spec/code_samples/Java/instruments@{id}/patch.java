// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.Address;
import com.checkout.common.CountryCode;
import com.checkout.common.Phone;
import com.checkout.common.four.AccountHolder;
import com.checkout.common.four.UpdateCustomerRequest;
import com.checkout.four.CheckoutApi;
import com.checkout.instruments.four.update.UpdateInstrumentCardRequest;
import com.checkout.instruments.four.update.UpdateInstrumentResponse;

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
