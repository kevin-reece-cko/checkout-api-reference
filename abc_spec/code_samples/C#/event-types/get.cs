// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Events.Previous;

Previous.ICheckoutApi api = CheckoutSdk.Builder()
    .Previous()
    .StaticKeys()
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

try
{
    /*
     Specify API version:
     "1.0" => Legacy API
     "2.0" => Unified Payments API
     null  => all versions
     */
                
    ItemsResponse<EventTypesResponse> response = await api.EventsClient().RetrieveAllEventTypes();
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