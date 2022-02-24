// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.FilePurpose;
import com.checkout.common.FileRequest;
import com.checkout.common.IdResponse;
import org.apache.http.entity.ContentType;

import java.io.File;

CheckoutApi api = CheckoutSdk.defaultSdk()
    .staticKeys()
    .publicKey("public_key")
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

File file = new File("evidence.pdf");
FileRequest fileRequest = FileRequest.builder()
    .file(file)
    .contentType(ContentType.create("application/pdf"))
    .purpose(FilePurpose.DISPUTE_EVIDENCE)
    .build();

try {
    IdResponse response = api.disputesClient().uploadFile(fileRequest).get();
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
