var api = CheckoutApi.Create("your secret key");

var webhookRetrievalResponse = await api.Webhooks.RetrieveWebhookAsync("wh_tdt72zlbe7cudogxdgit6nwk6i");