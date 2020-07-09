CheckoutApi api = CheckoutApiImpl.create(sk_XXXX, true, pk_XXXX);

List < WebhookResponse > response = api.webhooksClient().retrieveWebhooks().get();
