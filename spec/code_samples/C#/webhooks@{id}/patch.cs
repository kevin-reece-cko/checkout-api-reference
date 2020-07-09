var api = CheckoutApi.Create("your secret key");

// Original webhook
var webhook = new Webhook()
{
  Url = "https://example.com/webhooks",
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
var webhookRegistrationResponse = await Api.Webhooks.RegisterWebhookAsync(new RegisterWebhookRequest(webhook));

// Partially update
webhook.Url += "/partially/updated";
webhook.Headers = null;
var webhookPartialUpdateResponse = await Api.Webhooks.PartiallyUpdateWebhookAsync(
                                                        webhookRegistrationResponse.Id,
                                                        new PartialUpdateWebhookRequest(webhook)
                                                      );