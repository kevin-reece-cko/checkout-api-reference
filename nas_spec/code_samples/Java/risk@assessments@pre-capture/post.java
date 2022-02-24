// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.Currency;
import com.checkout.common.CustomerRequest;
import com.checkout.four.CheckoutApi;
import com.checkout.risk.Device;
import com.checkout.risk.RiskPayment;
import com.checkout.risk.RiskShippingDetails;
import com.checkout.risk.precapture.AuthenticationResult;
import com.checkout.risk.precapture.AuthorizationResult;
import com.checkout.risk.precapture.PreCaptureAssessmentRequest;
import com.checkout.risk.precapture.PreCaptureAssessmentResponse;
import com.checkout.risk.source.CustomerSourcePrism;

CheckoutApi api = CheckoutSdk.fourSdk()
    .staticKeys()
    .publicKey("public_key")
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

PreCaptureAssessmentRequest request = PreCaptureAssessmentRequest.builder()
    .date(Instant.MAX)
    .source(CustomerSourcePrism.builder().build())
    .customer(new CustomerRequest(null, "email@docs.checkout.com", "Name"))
    .payment(RiskPayment.builder().build())
    .shipping(RiskShippingDetails.builder().build())
    .amount(10L)
    .currency(Currency.GBP)
    .device(Device.builder().build())
    .metadata(new HashMap<>())
    .authenticationResult(AuthenticationResult.builder()
        .attempted(true)
        .challenged(true)
        .liabilityShifted(true)
        .method("3ds")
        .succeeded(true)
        .version("2.0")
        .build())
    .authorizationResult(AuthorizationResult.builder()
        .avsCode("V")
        .cvvResult("N")
        .build())
    .build();

try {
    PreCaptureAssessmentResponse response = api.riskClient().requestPreCaptureRiskScan(request).get();
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
