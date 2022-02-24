// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Forex;

Four.ICheckoutApi api = CheckoutSdk.FourSdk().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(FourOAuthScope.Fx)
    .Environment(Environment.Sandbox)
    .FilesEnvironment(Environment.Sandbox)
    .Build();

QuoteRequest request = new QuoteRequest()
{
    SourceCurrency = Currency.GBP,
    SourceAmount = 10,
    DestinationCurrency = Currency.USD,
    ProcessChannelId = "pc_abcdefghijklmnopqrstuvwxyz"
};

try
{
    QuoteResponse response = api.ForexClient().RequestQuote(request).Result;
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