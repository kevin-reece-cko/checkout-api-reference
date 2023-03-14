// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Sessions;
using Checkout.Sessions.Channel;

ICheckoutApi api = CheckoutSdk.Builder().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(OAuthScope.SessionsApp, OAuthScope.SessionsBrowser)
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

ThreeDsMethodCompletionRequest request = new ThreeDsMethodCompletionRequest()
{
    ThreeDsMethodCompletion = ThreeDsMethodCompletion.Y
};

try
{
    GetSessionResponseAfterChannelDataSupplied response = await api.SessionsClient()
        .Update3dsMethodCompletionIndicator("session_secret", "session_id", request);
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