var api = CheckoutApi.Create("your secret key");

var webhook = new Webhook()
{
  Url = "https://example.com/webhook",
  EventTypes = new List<string>
    {
        "payment_pending",
        "payment_captured"
    },
  Headers = new Dictionary<string, string>
    {
        { "Authorization", "1234" }
    }
};

var webhookRegistrationResponse = await api.Webhooks.RegisterWebhookAsync(new RegisterWebhookRequest(webhook));