CheckoutApi api = CheckoutApiImpl.create(sk_XXXX, true, pk_XXXX);

EventNotificationResponse notification = api.eventsClient().retrieveEventNotification("evt_c2qelfixai2u3es3ksovngkx3e", "ntf_wqjkqpgjy33uxoywcej4fnw6qm").get();
