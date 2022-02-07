// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Workflows.Four.Actions;
using Checkout.Workflows.Four.Actions.Request;

//API keys
Four.ICheckoutApi api = CheckoutSdk.FourSdk().StaticKeys()
    .PublicKey("public_key")
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

//OAuth
Four.ICheckoutApi api = CheckoutSdk.FourSdk().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(FourOAuthScope.FlowWorkflows)
    .Environment(Environment.Sandbox)
    .FilesEnvironment(Environment.Sandbox)
    .Build();

WorkflowActionRequest request = new WebhookWorkflowActionRequest()
{
    Url = "https://example.com/webhooks/checkout",
    Headers = new Dictionary<string, string>()
    {
        {"Authorization", "70ed20ff-ba31-4ea3-b3ef-772f2be1cbdf"}
    },
    Signature = new WebhookSignature()
    {
        Method = "HMACSHA256",
        Key = "public-signing-key"
    }
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