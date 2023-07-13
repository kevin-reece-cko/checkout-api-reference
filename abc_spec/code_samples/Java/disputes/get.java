// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.disputes.DisputeStatus;
import com.checkout.disputes.DisputesQueryFilter;
import com.checkout.disputes.DisputesQueryResponse;
import com.checkout.previous.CheckoutApi;

CheckoutApi api = CheckoutSdk
    .builder()
    .previous()
    .staticKeys()
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

DisputesQueryFilter query = DisputesQueryFilter.builder()
    .paymentId("payment_id")
    .from(Instant.parse(LocalDateTime.now().minusMonths(2).toInstant(ZoneOffset.UTC).toString()))
    .to(Instant.parse(LocalDateTime.now().toString()))
    .paymentArn("payment_arn")
    .paymentReference("payment_reference")
    .statuses(String.join(",", DisputeStatus.EVIDENCE_REQUIRED.getStatus(), DisputeStatus.ACCEPTED.getStatus()))
    .limit(10)
    .skip(5)
    .build();

try {
    DisputesQueryResponse response = api.disputesClient().query(query).get();
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
