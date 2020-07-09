var api = CheckoutApi.Create("your secret key");

var getDisputesRequest = new GetDisputesRequest(limit: 5);
var getDisputesResponse = await api.Disputes.GetDisputesAsync(getDisputesRequest: getDisputesRequest);