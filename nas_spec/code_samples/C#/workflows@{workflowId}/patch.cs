// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Workflows.Four;

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

UpdateWorkflowRequest request = new UpdateWorkflowRequest {Name = "workflow_name"};

try
{
    UpdateWorkflowResponse response = api.WorkflowsClient().UpdateWorkflow("workflow_id", request).Result;
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