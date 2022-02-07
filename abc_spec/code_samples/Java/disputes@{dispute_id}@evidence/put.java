// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.disputes.DisputeEvidenceRequest;

CheckoutApi api = CheckoutSdk.defaultSdk()
    .staticKeys()
    .publicKey("public_key")
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

DisputeEvidenceRequest evidenceRequest = DisputeEvidenceRequest.builder()
    .proofOfDeliveryOrServiceFile("file_id")
    .proofOfDeliveryOrServiceText("proof of delivery or service text")
    .invoiceOrReceiptFile("file_id")
    .invoiceOrReceiptText("Copy of the invoice")
    .invoiceShowingDistinctTransactionsFile("")
    .invoiceShowingDistinctTransactionsText("Copy of invoice #1244 showing two transactions")
    .customerCommunicationFile("file_id")
    .customerCommunicationText("Copy of an email exchange with the cardholder")
    .refundOrCancellationPolicyFile("file_id")
    .refundOrCancellationPolicyText("Copy of the refund policy")
    .recurringTransactionAgreementFile("file_id")
    .recurringTransactionAgreementText("Copy of the recurring transaction agreement")
    .additionalEvidenceFile("file_id")
    .additionalEvidenceText("Scanned document")
    .proofOfDeliveryOrServiceDateFile("file_id")
    .proofOfDeliveryOrServiceDateText("Copy of the customer receipt showing the merchandise was delivered on 2018-12-20")
    .build();

try {
    api.disputesClient().putEvidence("dispute_id", evidenceRequest).get();
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
