// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Sessions;
using Checkout.Sessions.Channel;

ICheckoutApi api = CheckoutSdk.Builder().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(OAuthScope.SessionsApp, OAuthScope.SessionsBrowser)
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

BrowserSession request = new BrowserSession
{
    AcceptHeader = "Accept:  *.*, q=0.1",
    JavaEnabled = true,
    Language = "FR-fr",
    ColorDepth = "16",
    ScreenWidth = "1920",
    ScreenHeight = "1080",
    Timezone = "60",
    UserAgent =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36",
    ThreeDsMethodCompletion = ThreeDsMethodCompletion.Y,
    IpAddress = "1.12.123.255"
};

try
{
    GetSessionResponse response = await api.SessionsClient().UpdateSession("session_id", request);
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