// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.Address;
import com.checkout.common.CountryCode;
import com.checkout.common.Phone;
import com.checkout.previous.CheckoutApi;
import com.checkout.sources.previous.SepaSourceRequest;
import com.checkout.sources.previous.SourceData;
import com.checkout.sources.previous.SourceResponse;

CheckoutApi api = CheckoutSdk
    .builder()
    .previous()
    .staticKeys()
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

Address billingAddress = Address.builder()
    .addressLine1("Checkout")
    .addressLine2("90 Tottenham Court Road")
    .city("London")
    .state("London")
    .zip("W1T 4TJ")
    .country(CountryCode.GB)
    .build();

SourceData sourceData = new SourceData();
    sourceData.put("first_name", "firstName");
    sourceData.put("last_name", "lastName");
    sourceData.put("account_iban", "iban");
    sourceData.put("bic", "bic");
    sourceData.put("billing_descriptor", "billingDescriptor");
    sourceData.put("mandate_type", "single");

SepaSourceRequest request = SepaSourceRequest.builder()
    .billingAddress(billingAddress)
    .phone(Phone.builder().countryCode("1").number("4155552671").build())
    .reference("reference")
    .sourceData(sourceData)
    .build();

try {
    SourceResponse response = api.sourcesClient().createSepaSource(request).get();
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