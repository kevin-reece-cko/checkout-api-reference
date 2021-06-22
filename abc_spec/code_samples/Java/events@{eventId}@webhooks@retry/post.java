CheckoutApi api = CheckoutApiImpl.create(sk_XXXX, true, pk_XXXX);

api.eventsClient().retryAllWebhooks("evt_c2qelfixai2u3es3ksovngkx3e").get();