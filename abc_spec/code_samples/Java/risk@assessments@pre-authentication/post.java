// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.Address;
import com.checkout.common.CountryCode;
import com.checkout.common.Currency;
import com.checkout.common.CustomerRequest;
import com.checkout.common.Phone;
import com.checkout.risk.Device;
import com.checkout.risk.Location;
import com.checkout.risk.RiskPayment;
import com.checkout.risk.RiskShippingDetails;
import com.checkout.risk.preauthentication.PreAuthenticationAssessmentRequest;
import com.checkout.risk.preauthentication.PreAuthenticationAssessmentResponse;
import com.checkout.risk.source.CardSourcePrism;

CheckoutApi api = CheckoutSdk.defaultSdk()
    .staticKeys()
    .publicKey("public_key")
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

CardSourcePrism cardSourcePrism = CardSourcePrism.builder()
    .billingAddress(Address.builder()
        .addressLine1("Checkout")
        .addressLine2("90 Tottenham Court Road")
        .city("London")
        .state("London")
        .zip("W1T 4TJ")
        .country(CountryCode.GB)
        .build())
    .expiryMonth(10)
    .expiryYear(2025)
    .number("123456789")
    .phone(Phone.builder().countryCode("1").number("415 555 2671").build())
    .build();

PreAuthenticationAssessmentRequest request = PreAuthenticationAssessmentRequest.builder()
    .date(Instant.now())
    .source(cardSourcePrism)
    .customer(new CustomerRequest(null, "email@docs.checkout.com", "Name"))
    .payment(RiskPayment.builder().psp("checkout").id("123456789").build())
    .shipping(RiskShippingDetails.builder().address(
        Address.builder()
            .addressLine1("Checkout")
            .addressLine2("90 Tottenham Court Road")
            .city("London")
            .state("London")
            .zip("W1T 4TJ")
            .country(CountryCode.GB)
            .build()
    ).build())
    .reference("reference")
    .description("description")
    .amount(10L)
    .currency(Currency.GBP)
    .device(Device.builder()
        .ip("90.197.169.245")
        .location(Location.builder().longitude("0.1313").latitude("51.5107").build())
        .type("Phone")
        .os("iOS")
        .model("iPhone X")
        .date(Instant.now())
        .userAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
        .fingerprint("34304a9e3fg09302")
        .build())
    .metadata(Stream.of(
        new AbstractMap.SimpleImmutableEntry<>("VoucherCode", "loyalty_10"),
        new AbstractMap.SimpleImmutableEntry<>("discountApplied", "10"),
        new AbstractMap.SimpleImmutableEntry<>("customer_id", "2190EF321"))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
    .build();

try {
    PreAuthenticationAssessmentResponse response = api.riskClient().requestPreAuthenticationRiskScan(request).get();
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
