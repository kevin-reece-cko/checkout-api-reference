var api = CheckoutApi.Create("your secret key");
var paymentId = "pay_y3oqhf46pyzuxjbcn2giaqnb44";

IEnumerable<PaymentAction> paymentActions = await api.Payments.GetActionsAsync(paymentId);