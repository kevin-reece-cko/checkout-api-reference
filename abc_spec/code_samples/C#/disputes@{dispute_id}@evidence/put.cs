// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Disputes;

Previous.ICheckoutApi api = CheckoutSdk.Builder()
	.Previous()
	.StaticKeys()
	.PublicKey("public_key")
	.SecretKey("secret_key")
	.Environment(Environment.Sandbox)
	.HttpClientFactory(new DefaultHttpClientFactory())
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