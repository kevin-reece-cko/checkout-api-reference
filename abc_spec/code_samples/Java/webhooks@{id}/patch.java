CheckoutApi api = CheckoutApiImpl.create(sk_XXXX, true, pk_XXXX);

WebhookResponse oldWebhook = api.webhooksClient().retrieveWebhook("wh_ahun3lg7bf4e3lohbhni65335u").get();
WebhookRequest webhookRequest = oldWebhook.toRequest();
webhookRequest.setUrl("https://example.com/webhooks/updated");

WebhookResponse newWebhook = api.webhooksClient().updateWebhook(id, webhookRequest).get();