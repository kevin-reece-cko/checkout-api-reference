// Please refer to https://github.com/checkout/checkout-sdk-net on how to setup the SDK with OAuth
try
{
    ICheckoutApi api = CheckoutSdk.Builder().OAuth()
        .ClientCredentials("client_id", "client_secret")
        .Scopes(OAuthScope.Vault) // more scopes available
        .Environment(Environment.Sandbox)
        .HttpClientFactory(new DefaultHttpClientFactory())
        .Build();
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