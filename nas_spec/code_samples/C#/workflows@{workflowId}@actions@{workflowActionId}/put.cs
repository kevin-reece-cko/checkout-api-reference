// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Workflows.Actions;
using Checkout.Workflows.Actions.Request;

//API keys
ICheckoutApi api = CheckoutSdk.Builder().StaticKeys()
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

//OAuth
ICheckoutApi api = CheckoutSdk.Builder().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(OAuthScope.Flow)
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

WorkflowActionRequest request = new WebhookWorkflowActionRequest
{
    Url = "https://example.com/webhooks/checkout",
    Headers = new Dictionary<string, string>
    {
        {"Authorization", "70ed20ff-ba31-4ea3-b3ef-772f2be1cbdf"}
    },
    Signature = new WebhookSignature {Method = "HMACSHA256", Key = "public-signing-key"}
};

try
{
    await api.WorkflowsClient().UpdateWorkflowAction("workflow_id", "action_id", request);
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