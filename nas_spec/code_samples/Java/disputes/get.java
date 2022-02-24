// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.disputes.DisputeStatus;
import com.checkout.disputes.four.DisputesQueryFilter;
import com.checkout.disputes.four.DisputesQueryResponse;
import com.checkout.four.CheckoutApi;

// API Keys
CheckoutApi api = CheckoutSdk.fourSdk()
    .staticKeys()
    .publicKey("public_key")
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

// OAuth
CheckoutApi api = CheckoutSdk.fourSdk()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .scopes(FourOAuthScope.DISPUTES) // more scopes available
    .build();

DisputesQueryFilter query = DisputesQueryFilter.builder()
    .paymentId("payment_id")
    .from(LocalDateTime.now().minusYears(2).toInstant(ZoneOffset.UTC))
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
