// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Disputes;

//API keys
ICheckoutApi api = CheckoutSdk.Builder().StaticKeys()
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

//OAuth
ICheckoutApi api = CheckoutSdk.Builder().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(OAuthScope.Disputes)
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

DisputeEvidenceRequest request = new DisputeEvidenceRequest
{
    ProofOfDeliveryOrServiceFile = "file_xxxxxx",
    ProofOfDeliveryOrServiceText = "proof of delivery or service text",
    InvoiceOrReceiptFile = "file_xxxxxx",
    InvoiceOrReceiptText = "Copy of the invoice",
    CustomerCommunicationFile = "file_xxxxxx",
    CustomerCommunicationText = "Copy of an email exchange with the cardholder",
    AdditionalEvidenceFile = "file_xxxxxx",
    AdditionalEvidenceText = "Scanned document",
    InvoiceShowingDistinctTransactionsFile = "file_xxxxxx",
    InvoiceShowingDistinctTransactionsText = "Copy of invoice #1244 showing two transactions",
    RefundOrCancellationPolicyFile = "file_xxxxxx",
    RefundOrCancellationPolicyText = "Copy of the refund policy",
    RecurringTransactionAgreementFile = "file_xxxxxx",
    RecurringTransactionAgreementText = "Copy of the recurring transaction agreement",
    ProofOfDeliveryOrServiceDateFile = "file_xxxxxx",
    ProofOfDeliveryOrServiceDateText = "Copy of the customer receipt showing the merchandise was delivered"
};

try
{
    EmptyResponse response = await api.DisputesClient().PutEvidence("disputes_id", request);
}
catch (CheckoutApiException e)
{
    // API error
    string requestId = e.RequestId;
    var statusCode = e.HttpStatusCode;
    IDictionary<string, object> errorDetails = e.ErrorDetails;
}
catch (CheckoutArgumentException e)
{
    // Bad arguments
}
catch (CheckoutAuthorizationException e)
{
    // Invalid authorization
}