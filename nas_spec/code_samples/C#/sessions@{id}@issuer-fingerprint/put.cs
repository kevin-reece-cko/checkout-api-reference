// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Sessions;
using Checkout.Sessions.Channel;

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
    .Scopes(FourOAuthScope.Sessions)
    .Environment(Environment.Sandbox)
    .FilesEnvironment(Environment.Sandbox)
    .Build();

ThreeDsMethodCompletionRequest request = new ThreeDsMethodCompletionRequest()
{
    ThreeDsMethodCompletion = ThreeDsMethodCompletion.Y
};

try
{
    GetSessionResponseAfterChannelDataSupplied response = api.SessionsClient().Update3dsMethodCompletionIndicator("session_secret", "session_id", request).Result;
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