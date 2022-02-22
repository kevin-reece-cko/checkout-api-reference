// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Reconciliation;

ICheckoutApi api = CheckoutSdk.DefaultSdk().StaticKeys()
    .PublicKey("public_key")
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

QueryFilterDateRange queryFilterDateRange = new QueryFilterDateRange
    {
        From = DateTime.Now.Subtract(TimeSpan.FromDays(30)), To = DateTime.Now
    };

try
{
    string content = api.ReconciliationClient().RetrieveCsvStatementsReport(queryFilterDateRange).Result;
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