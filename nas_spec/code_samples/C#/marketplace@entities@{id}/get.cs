// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Marketplace;

Four.ICheckoutApi api = CheckoutSdk.FourSdk().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(FourOAuthScope.Marketplace)
    .Environment(Environment.Sandbox)
    .FilesEnvironment(Environment.Sandbox)
    .Build();

try
{
    OnboardEntityDetailsResponse response = api.MarketplaceClient().GetEntity("entity_id").Result;
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