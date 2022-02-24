// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Workflows.Four.Reflows;

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

ReflowRequest request = new ReflowByEventsRequest()
{
    Events = new List<string>()
    {
        "evt_lzmo6p0i3612judj754w1ngtil",
        "evt_05z6xuagtti48ajyfbuekg6a0a"
    },
    Workflows = new List<string>()
    {
        "wf_sq8jnqi9i749hhb470bu308uk2",
        "wf_bz91q7i4ks4sr0kasmas2xhp56"

    }
};

try
{
    ReflowResponse response = api.WorkflowsClient().Reflow(request).Result;
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