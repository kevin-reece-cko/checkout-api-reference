var api = CheckoutApi.Create("your secret key");

var webhooksRetrievalResponse = await api.Webhooks.RetrieveWebhooksAsync();