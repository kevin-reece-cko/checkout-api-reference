// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Payments;

ICheckoutApi api = CheckoutSdk.DefaultSdk().StaticKeys()
    .PublicKey("public_key")
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

RefundRequest request = new RefundRequest()
{
    Amount = 10,
    Reference = "reference",
    Metadata = new Dictionary<string, object>()
    {
        {"coupon_code", "NY2018"},
        {"partner_id", "123989"}
    }
};

try
{
    RefundResponse response = await api.PaymentsClient().RefundPayment("payment_id", request);
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