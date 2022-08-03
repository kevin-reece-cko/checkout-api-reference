// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.ContentResponse;
import com.checkout.Environment;
import com.checkout.common.QueryFilterDateRange;
import com.checkout.previous.CheckoutApi;

CheckoutApi api = CheckoutSdk
    .builder()
    .previous()
    .staticKeys()
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

QueryFilterDateRange filter = QueryFilterDateRange
    .builder()
    .from(Instant.parse(LocalDateTime.now().minusMonths(2).toInstant(ZoneOffset.UTC).toString()))
    .to(Instant.parse(LocalDateTime.now().toInstant(ZoneOffset.UTC).toString()))
    .build();

try {
    // The second parameter is optional. Specifies the path where a file with the content returned is saved. If the file
    // does not exist, the client will attempt to create a new one, otherwise the existing file will be rewritten.
    ContentResponse response = api.reconciliationClient().retrieveCSVStatementsReport(filter, "path/to/download").get();
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
