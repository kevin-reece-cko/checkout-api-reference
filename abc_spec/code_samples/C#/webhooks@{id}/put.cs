var api = CheckoutApi.Create("your secret key");

var updatedWebhook = new Webhook()
{
  Url = "https://example.com/webhooks/updated",
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

var webhookUpdateResponse = await api.Webhooks.UpdateWebhookAsync(
                                    "wh_ahun3lg7bf4e3lohbhni65335u",
                                    new UpdateWebhookRequest(updatedWebhook)
                                  );