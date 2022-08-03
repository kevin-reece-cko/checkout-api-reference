// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.previous.CheckoutApi;
import com.checkout.reconciliation.previous.ReconciliationPaymentReportResponse;
import com.checkout.reconciliation.previous.ReconciliationQueryPaymentsFilter;

CheckoutApi api = CheckoutSdk
    .builder()
    .previous()
    .staticKeys()
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

ReconciliationQueryPaymentsFilter filter = ReconciliationQueryPaymentsFilter
    .builder()
    .from(Instant.parse(LocalDateTime.now().minusMonths(2).toInstant(ZoneOffset.UTC).toString()))
    .to(Instant.parse(LocalDateTime.now().toInstant(ZoneOffset.UTC).toString()))
    .reference("reference")
    .limit(1)
    .build();

try {
    ReconciliationPaymentReportResponse response = api.reconciliationClient().queryPaymentsReport(filter).get();
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
