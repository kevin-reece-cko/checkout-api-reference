// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.accounts.AccountPhone;
import com.checkout.accounts.ContactDetails;
import com.checkout.accounts.DateOfBirth;
import com.checkout.accounts.Document;
import com.checkout.accounts.DocumentType;
import com.checkout.accounts.Identification;
import com.checkout.accounts.Individual;
import com.checkout.accounts.OnboardEntityRequest;
import com.checkout.accounts.OnboardEntityResponse;
import com.checkout.accounts.Profile;
import com.checkout.common.Address;
import com.checkout.common.CountryCode;

// OAuth
CheckoutApi api = CheckoutSdk.builder()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .scopes(OAuthScope.ACCOUNTS) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

OnboardEntityRequest onboardEntityRequest = OnboardEntityRequest.builder()
    .reference("reference")
    .contactDetails(ContactDetails.builder()
        .phone(AccountPhone.builder().number("2345678910").build())
        .build())
    .profile(Profile.builder()
        .urls(Arrays.asList("https://docs.checkout.com/1", "https://docs.checkout.com/2"))
        .mccs(Collections.singletonList("0742"))
        .build())
    .individual(Individual.builder()
        .firstName("FirstName")
        .lastName("LastName")
        .tradingName("TradingName")
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
            .year(1990)
            .build())
        .identification(Identification.builder()
            .nationalIdNumber("AB123456C")
            .document(Document.builder()
                .back("number")
                .front("number")
                .type(DocumentType.DRIVING_LICENSE)
                .build())
            .build())
        .build())
    .build();

try {
    OnboardEntityResponse response = api.accountsClient().createEntity(onboardEntityRequest).get();
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
