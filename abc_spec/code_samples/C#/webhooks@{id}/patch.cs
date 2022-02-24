// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Webhooks;

ICheckoutApi api = CheckoutSdk.DefaultSdk().StaticKeys()
    .PublicKey("public_key")
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

WebhookRequest request = new WebhookRequest()
{
    Url = "https://example.com/webhooks",
    Active = true,
    Headers = new Dictionary<string, string>()
    {
        {"authorization", "1234"}
    },
    ContentType = WebhookContentType.Json,
    EventTypes = new List<string>()
    {
        "payment_approved",
        "payment_pending",
        "payment_declined",
        "payment_expired",
        "payment_canceled",
        "payment_voided",
        "payment_void_declined",
        "payment_captured",
        "payment_capture_declined",
        "payment_capture_pending",
        "payment_refunded",
        "payment_refund_declined",
        "payment_refund_pending"
    }
};

try
{
    WebhookResponse response = api.WebhooksClient().PatchWebhook("webhook_id", request).Result;
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