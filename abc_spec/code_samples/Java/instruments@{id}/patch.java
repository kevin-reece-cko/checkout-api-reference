// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.Address;
import com.checkout.common.CountryCode;
import com.checkout.common.Phone;
import com.checkout.instruments.previous.InstrumentAccountHolder;
import com.checkout.instruments.previous.UpdateInstrumentRequest;
import com.checkout.instruments.previous.UpdateInstrumentResponse;
import com.checkout.previous.CheckoutApi;

CheckoutApi api = CheckoutSdk
    .builder()
    .previous()
    .staticKeys()
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
