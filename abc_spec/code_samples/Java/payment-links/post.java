// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.Address;
import com.checkout.common.ChallengeIndicator;
import com.checkout.common.CountryCode;
import com.checkout.common.Currency;
import com.checkout.common.CustomerRequest;
import com.checkout.common.Phone;
import com.checkout.common.Product;
import com.checkout.payments.BillingDescriptor;
import com.checkout.payments.BillingInformation;
import com.checkout.payments.PaymentRecipient;
import com.checkout.payments.PaymentType;
import com.checkout.payments.ProcessingSettings;
import com.checkout.payments.RiskRequest;
import com.checkout.payments.ShippingDetails;
import com.checkout.payments.ThreeDSRequest;
import com.checkout.payments.links.PaymentLinkRequest;
import com.checkout.payments.links.PaymentLinkResponse;

CheckoutApi api = CheckoutSdk.defaultSdk()
        .staticKeys()
        .publicKey("public_key")
        .secretKey("secret_key")
        .environment(Environment.SANDBOX) // or Environment.PRODUCTION
        .build();

PaymentLinkRequest paymentLinksRequest = PaymentLinkRequest.builder()
    .amount(10L)
    .currency(Currency.GBP)
    .reference("reference")
    .description("description")
    .expiresIn(604800)
    .customer(new CustomerRequest(null, "email@docs.checkout.com", "Name"))
    .shipping(ShippingDetails.builder()
        .address(Address.builder()
            .addressLine1("Checkout")
            .addressLine2("90 Tottenham Court Road")
            .city("London")
            .state("London")
            .zip("W1T 4TJ")
            .country(CountryCode.GB)
            .build())
        .phone(Phone.builder().countryCode("1").number("415 555 2671").build())
        .build())
    .billing(BillingInformation.builder()
        .address(Address.builder()
            .addressLine1("Checkout")
            .addressLine2("90 Tottenham Court Road")
            .city("London")
            .state("London")
            .zip("W1T 4TJ")
            .country(CountryCode.GB)
            .build())
        .phone(Phone.builder().countryCode("1").number("415 555 2671").build())
        .build())
    .recipient(PaymentRecipient.builder()
        .accountNumber("999999999")
        .country(CountryCode.GB)
        .dateOfBirth("1985-05-15")
        .firstName("FirstName")
        .lastName("LastName")
        .zip("12345")
        .build())
    .processing(ProcessingSettings.builder()
        .aft(true)
        .build())
    .capture(true)
    .captureOn(Instant.now().plus(30, ChronoUnit.DAYS))
    .products(Collections.singletonList(Product.builder()
        .name("name")
        .quantity(1L)
        .price(200L)
        .build()))
    .threeDS(ThreeDSRequest.builder()
        .enabled(Boolean.FALSE)
        .attemptN3D(Boolean.FALSE)
        .challengeIndicator(ChallengeIndicator.NO_CHALLENGE_REQUESTED)
        .build())
    .risk(new RiskRequest(Boolean.FALSE))
    .returnUrl("https://docs.checkout.com/success")
    .locale("en-GB")
    .paymentType(PaymentType.REGULAR)
    .billingDescriptor(BillingDescriptor.builder()
        .city("London")
        .name("name")
        .build())
    .build();

try {
    PaymentLinkResponse response = api.paymentLinksClient().createAsync(paymentLinksRequest).get();
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
