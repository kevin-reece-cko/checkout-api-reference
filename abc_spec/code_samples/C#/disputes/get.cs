// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Disputes;

Previous.ICheckoutApi api = CheckoutSdk.Builder()
    .Previous()
    .StaticKeys()
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

DisputesQueryFilter request = new DisputesQueryFilter
{
    Limit = 250,
    To = DateTime.Now,
};

try
{
    DisputesQueryResponse response = await api.DisputesClient().Query(request);
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