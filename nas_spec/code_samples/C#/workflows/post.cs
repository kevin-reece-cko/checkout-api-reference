// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Workflows;
using Checkout.Workflows.Actions;
using Checkout.Workflows.Actions.Request;
using Checkout.Workflows.Conditions.Request;


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

CreateWorkflowRequest request = new CreateWorkflowRequest
{
    Name = "Webhooks workflow",
    Conditions = new List<WorkflowConditionRequest>
    {
        new EventWorkflowConditionRequest
        {
            Events = new Dictionary<string, ISet<string>>
            {
                {
                    "gateway",
                    new HashSet<string>
                    {
                        "payment_approved",
                        "payment_declined",
                        "card_verification_declined",
                        "card_verified",
                        "payment_authorization_incremented",
                        "payment_authorization_increment_declined",
                        "payment_capture_declined",
                        "payment_captured",
                        "payment_refund_declined",
                        "payment_refunded",
                        "payment_void_declined",
                        "payment_voided"
                    }
                },
                {
                    "dispute",
                    new HashSet<string>
                    {
                        "dispute_canceled",
                        "dispute_evidence_required",
                        "dispute_expired",
                        "dispute_lost",
                        "dispute_resolved",
                        "dispute_won"
                    }
                }
            }
        },
        new EntityWorkflowConditionRequest
        {
            Entities = new List<string>
            {
                "ent_xyfdshfudosfdshfdiosfds", "ent_fidjosfjdisofdjsifdosfu"
            }
        },
        new ProcessingChannelWorkflowConditionRequest
        {
            ProcessingChannels = new List<string> {"pc_axclravnqf5u5ejkweijnp5zc4"}
        }
    },
    Actions = new List<WorkflowActionRequest>
    {
        new WebhookWorkflowActionRequest
        {
            Url = "https://example.com/webhooks",
            Headers = new Dictionary<string, string>
            {
                {"Authorization", "70ed20ff-ba31-4ea3-b3ef-772f2be1cbdf"}
            },
            Signature = new WebhookSignature
            {
                Method = "HMACSHA256", Key = "8V8x0dLK%AyD*DNS8JJr"
            }
        }
    }
};

try
{
    CreateWorkflowResponse response = await api.WorkflowsClient().CreateWorkflow(request);
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