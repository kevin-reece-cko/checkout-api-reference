var api = CheckoutApi.Create("your secret key");
var sessionId = "sid_y3oqhf46pyzuxjbcn2giaqnb44";
GetPaymentResponse payment = await api.Payments.GetAsync(sessionId);

if (payment.Approved)
{            
    var cardSourceId = payment.Source.AsCard().Id;
}