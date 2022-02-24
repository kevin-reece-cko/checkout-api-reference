// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.QueryFilterDateRange;
import com.checkout.reconciliation.StatementReportResponse;

CheckoutApi api = CheckoutSdk.defaultSdk()
    .staticKeys()
    .publicKey("public_key")
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

QueryFilterDateRange filter = QueryFilterDateRange
    .builder()
    .from(LocalDateTime.now().minusYears(2).toInstant(ZoneOffset.UTC).toString())
    .to(LocalDateTime.now().toInstant(ZoneOffset.UTC).toString())
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
