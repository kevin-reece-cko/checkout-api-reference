// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.Address;
import com.checkout.common.CountryCode;
import com.checkout.common.Phone;
import com.checkout.instruments.InstrumentAccountHolder;
import com.checkout.instruments.UpdateInstrumentRequest;
import com.checkout.instruments.UpdateInstrumentResponse;

CheckoutApi api = CheckoutSdk.defaultSdk()
    .staticKeys()
    .publicKey("public_key")
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

UpdateInstrumentRequest updateRequest = UpdateInstrumentRequest.builder()
    .name("New name")
    .expiryMonth(10)
    .expiryYear(2025)
    .accountHolder(InstrumentAccountHolder.builder()
        .billingAddress(Address.builder()
            .addressLine1("Checkout")
            .addressLine2("90 Tottenham Court Road")
            .city("London")
            .state("London")
            .zip("W1T 4TJ")
            .country(CountryCode.GB)
            .build())
        .phone(Phone.builder().countryCode("1").number("415 555 2671").build())
        .build())
    .customer(UpdateInstrumentRequest.Customer.builder()
        .id("customer_id")
        .isDefault(true)
        .build())
    .build();

try {
    UpdateInstrumentResponse response = api.instrumentsClient().updateInstrument("instrument_id", updateRequest).get();
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
