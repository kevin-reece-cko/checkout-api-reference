CheckoutApi api = CheckoutApiImpl.create(sk_XXXX, true, pk_XXXX);

List < String > eventTypes = Arrays.asList("payment_captured", "payment_approved", "payment_declined");

WebhookRequest webhookRequest = WebhookRequest.builder()
  .url("https://example.com/webhooks/updated")
  .eventTypes(eventTypes)
  .build();

WebhookResponse webhook = api.webhooksClient().updateWebhook("wh_ahun3lg7bf4e3lohbhni65335u", webhookRequest).get();