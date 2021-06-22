CheckoutApi api = CheckoutApiImpl.create(sk_XXXX, true, pk_XXXX);

WebhookResponse webhook = api.webhooksClient().retrieveWebhook("wh_tdt72zlbe7cudogxdgit6nwk6i").get();