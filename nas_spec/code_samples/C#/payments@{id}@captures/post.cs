// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Payments;

//API keys
ICheckoutApi api = CheckoutSdk.Builder().StaticKeys()
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

//OAuth
ICheckoutApi api = CheckoutSdk.Builder().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(OAuthScope.Gateway)
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

CaptureRequest request = new CaptureRequest
{
    Amount = 10,
    Reference = "reference",
    Metadata = new Dictionary<string, object>() {{"coupon_code", "NY2018"}, {"partner_id", "123989"}}
};

try
{
    CaptureResponse response = await api.PaymentsClient().CapturePayment("payment_id", request);
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