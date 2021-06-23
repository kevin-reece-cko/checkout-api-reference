var api = CheckoutApi.Create("your secret key");
var paymentId = "pay_y3oqhf46pyzuxjbcn2giaqnb44";

// Full capture
await api.Payments.CaptureAsync(paymentId);

// Or partial capture
var captureRequest = new CaptureRequest
{
  Reference = "your reference",
  Amount = 100
};

await api.Payments.CaptureAsync(paymentId, captureRequest);