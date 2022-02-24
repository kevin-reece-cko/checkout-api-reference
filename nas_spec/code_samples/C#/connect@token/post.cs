// Please refer to https://github.com/checkout/checkout-sdk-net on how to setup the SDK with OAuth
try
{
    Four.ICheckoutApi api = CheckoutSdk.FourSdk().OAuth()
        .ClientCredentials("client_id", "client_secret")
        .Scopes(FourOAuthScope.Files, FourOAuthScope.Flow, FourOAuthScope.Fx, FourOAuthScope.Gateway,
            FourOAuthScope.Marketplace, FourOAuthScope.SessionsApp, FourOAuthScope.SessionsBrowser,
            FourOAuthScope.Vault, FourOAuthScope.PayoutsBankDetails) // more scopes available
        .Environment(Environment.Sandbox)
        .FilesEnvironment(Environment.Sandbox)
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