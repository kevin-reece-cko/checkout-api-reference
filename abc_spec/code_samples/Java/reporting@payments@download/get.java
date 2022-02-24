// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.QueryFilterDateRange;

CheckoutApi api = CheckoutSdk.defaultSdk()
    .staticKeys()
    .publicKey("public_key")
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

QueryFilterDateRange queryFilterDateRange = QueryFilterDateRange.builder()
    .from(LocalDateTime.now().minus(1, ChronoUnit.MONTHS).toInstant(ZoneOffset.UTC))
    .to(Instant.now())
    .build();

try {
    // The second parameter is optional. Specifies the path where a file with the content returned is saved. If the file
    // does not exist, the client will attempt to create a new one, otherwise the existing file will be rewritten.
    String content = api.reconciliationClient().retrieveCSVPaymentReport(queryFilterDateRange, "/path/to/file.csv").get();
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
