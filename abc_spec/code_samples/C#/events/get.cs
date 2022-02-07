// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Events;

ICheckoutApi api = CheckoutSdk.DefaultSdk().StaticKeys()
	.PublicKey("public_key")
	.SecretKey("secret_key")
	.Environment(Environment.Sandbox)
	.HttpClientFactory(new DefaultHttpClientFactory())
	.Build();

RetrieveEventsRequest request = new RetrieveEventsRequest()
{
	PaymentId = "payment_id"
};

try
{
	EventsPageResponse response = api.EventsClient().RetrieveEvents(request).Result;
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