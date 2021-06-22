var api = CheckoutApi.Create("your secret key");
var paymentId = "pay_y3oqhf46pyzuxjbcn2giaqnb44";

await api.Payments.VoidAsync(paymentId);