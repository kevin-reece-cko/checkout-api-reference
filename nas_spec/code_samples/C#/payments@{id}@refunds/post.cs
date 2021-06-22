var api = CheckoutApi.Create("your secret key");
var paymentId = "pay_y3oqhf46pyzuxjbcn2giaqnb44";

// Full refund
await api.Payments.RefundAsync(paymentId);

// Or partial refund
var refundRequest = new RefundRequest
{
    Reference = "your reference",
    Amount = 100
};

await api.Payments.RefundAsync(paymentId, refundRequest);
