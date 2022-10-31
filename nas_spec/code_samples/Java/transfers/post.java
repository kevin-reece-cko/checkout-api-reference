// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.transfers.CreateTransferResponse;
import com.checkout.transfers.CreateTransferRequest;
import com.checkout.transfers.TransferDestinationRequest;
import com.checkout.transfers.TransferSourceRequest;
import com.checkout.transfers.TransferType;

// OAuth
CheckoutApi api = CheckoutSdk.builder()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .scopes(OAuthScope.TRANSFERS_CREATE) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

CreateTransferRequest request = CreateTransferRequest.builder()
    .reference("reference")
    .transferType(TransferType.COMMISSION)
        .source(TransferSourceRequest.builder()
        .id("entity_id")
        .amount(100L)
        .build())
    .destination(TransferDestinationRequest.builder()
        .id("destination_id")
        .build())
    .build();

try {
    CreateTransferResponse response = api.transfersClient().initiateTransferOfFunds(request).get();
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
