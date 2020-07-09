CheckoutApi api = CheckoutApiImpl.create(sk_XXXX, true, pk_XXXX);

List<EventTypesResponse> allEventTypesResponses = api.eventsClient().retrieveAllEventTypes(null).get();
