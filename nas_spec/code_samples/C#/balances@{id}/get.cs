// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Balances;
using Checkout.Common;

ICheckoutApi api = CheckoutSdk.Builder().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(OAuthScope.Balances)
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();


BalancesQuery query = new BalancesQuery {Query = "currency:" + Currency.GBP};

try
{
    BalancesResponse balances = await api.BalancesClient()
        .RetrieveEntityBalances("entitiy_id", query);
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