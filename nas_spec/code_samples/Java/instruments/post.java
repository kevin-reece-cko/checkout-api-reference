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
import com.checkout.instruments.create.CreateCustomerInstrumentRequest;
import com.checkout.instruments.create.CreateInstrumentResponse;
import com.checkout.instruments.create.CreateInstrumentTokenRequest;

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

CreateInstrumentTokenRequest request = CreateInstrumentTokenRequest.builder()
    .token("token")
    .accountHolder(AccountHolder.builder()
        .firstName("FirstName")
        .lastName("LastName")
        .phone(Phone.builder().countryCode("1").number("415 555 2671").build())
        .billingAddress(Address.builder()
            .addressLine1("CheckoutSdk.com")
            .addressLine2("90 Tottenham Court Road")
            .city("London")
            .state("London")
            .zip("W1T 4TJ")
            .country(CountryCode.GB)
            .build())
        .build())
    .customer(CreateCustomerInstrumentRequest.builder()
        .id("customer_id")
        .build())
    .build();

try {
    CreateInstrumentResponse response = api.instrumentsClient().create(request).get();
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
