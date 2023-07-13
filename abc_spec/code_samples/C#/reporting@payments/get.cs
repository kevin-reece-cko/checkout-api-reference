// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Reconciliation.Previous;

Previous.ICheckoutApi api = CheckoutSdk.Builder()
    .Previous()
    .StaticKeys()
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

ReconciliationQueryPaymentsFilter request = new ReconciliationQueryPaymentsFilter() {Limit = 50};

try
{
    ReconciliationPaymentReportResponse response = await api.ReconciliationClient().QueryPaymentsReport(request);
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