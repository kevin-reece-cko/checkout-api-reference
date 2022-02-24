// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Disputes;

//API keys
Four.ICheckoutApi api = CheckoutSdk.FourSdk().StaticKeys()
    .PublicKey("public_key")
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

//OAuth
Four.ICheckoutApi api = CheckoutSdk.FourSdk().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(FourOAuthScope.Disputes)
    .Environment(Environment.Sandbox)
    .FilesEnvironment(Environment.Sandbox)
    .Build();

DisputeEvidenceRequest request = new DisputeEvidenceRequest()
{
    ProofOfDeliveryOrServiceFile = "file_xxxxxx",
    ProofOfDeliveryOrServiceText = "proof of delivery or service text",
    InvoiceOrReceiptFile = "file_xxxxxx",
    InvoiceOrReceiptText = "Copy of the invoice",
    CustomerCommunicationFile = "file_xxxxxx",
    CustomerCommunicationText = "Copy of an email exchange with the cardholder",
    AdditionalEvidenceFile = "file_xxxxxx",
    AdditionalEvidenceText = "Scanned document"
};

try
{
    await api.DisputesClient().PutEvidence("disputes_id", request);
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