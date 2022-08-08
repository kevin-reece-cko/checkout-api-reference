// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.disputes.DisputeStatus;
import com.checkout.disputes.DisputesQueryFilter;
import com.checkout.disputes.DisputesQueryResponse;

// API Keys
CheckoutApi api = CheckoutSdk.builder()
    .staticKeys()
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

// OAuth
CheckoutApi api = CheckoutSdk.builder()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .scopes(OAuthScope.DISPUTES) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

DisputesQueryFilter query = DisputesQueryFilter.builder()
    .paymentId("payment_id")
    .from(LocalDateTime.now().minusMonths(2).toInstant(ZoneOffset.UTC))
    .to(Instant.now())
    .paymentArn("payment_arn")
    .paymentReference("payment_reference")
    .statuses(String.join(",", DisputeStatus.EVIDENCE_UNDER_REVIEW.getStatus(), DisputeStatus.ARBITRATION_WON.getStatus()))
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
