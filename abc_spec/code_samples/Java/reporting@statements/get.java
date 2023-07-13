// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.QueryFilterDateRange;
import com.checkout.previous.CheckoutApi;
import com.checkout.reconciliation.previous.StatementReportResponse;

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
    StatementReportResponse response = api.reconciliationClient().queryStatementsReport(filter).get();
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
