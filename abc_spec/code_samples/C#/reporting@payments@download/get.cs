// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Reconciliation;

Previous.ICheckoutApi api = CheckoutSdk.Builder()
    .Previous()
    .StaticKeys()
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
    ContentsResponse retrieveCsvPaymentReport = await api.ReconciliationClient().RetrieveCsvPaymentReport(queryFilterDateRange);
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