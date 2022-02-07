// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.Address;
import com.checkout.common.CountryCode;
import com.checkout.four.CheckoutApi;
import com.checkout.marketplace.ContactDetails;
import com.checkout.marketplace.DateOfBirth;
import com.checkout.marketplace.Document;
import com.checkout.marketplace.DocumentType;
import com.checkout.marketplace.Identification;
import com.checkout.marketplace.Individual;
import com.checkout.marketplace.OnboardEntityRequest;
import com.checkout.marketplace.OnboardEntityResponse;
import com.checkout.marketplace.Phone;
import com.checkout.marketplace.Profile;

CheckoutApi api = CheckoutSdk.fourSdk()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .scopes(FourOAuthScope.MARKETPLACE) // more scopes available
    .build();

OnboardEntityRequest request = OnboardEntityRequest.builder()
    .reference("reference")
    .contactDetails(ContactDetails.builder()
        .phone(Phone.builder().number("415 555 2671").build())
        .build())
    .profile(Profile.builder()
        .urls(Collections.singletonList("https://docs.checkout.com"))
        .mccs(Collections.singletonList("0742"))
        .build())
    .individual(Individual.builder()
        .firstName("FirstName")
        .lastName("LastName")
        .tradingName("Trading")
        .registeredAddress(Address.builder()
            .addressLine1("Checkout.com")
            .addressLine1("90 Tottenham Court Road")
            .city("London")
            .state("London")
            .zip("WIT 4TJ")
            .country(CountryCode.GB)
            .build())
        .nationalTaxId("TAX123456")
        .dateOfBirth(DateOfBirth.builder()
            .day(5)
            .month(6)
            .year(1995)
            .build())
        .identification(Identification.builder()
            .nationalIdNumber("AB123456C")
            .document(Document.builder()
                    .back("number")
                    .front("number")
                    .type(DocumentType.PASSPORT)
                    .build())
            .build())
        .build())
    .build();

try {
    OnboardEntityResponse response = api.marketplaceClient().updateEntity(request, "entity_id").get();
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
