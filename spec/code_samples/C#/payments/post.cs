var api = CheckoutApi.Create("your secret key");
var tokenSource = new TokenSource("tok_ubfj2q76miwundwlk72vxt2i7q");
var paymentRequest = new PaymentRequest<TokenSource>(tokenSource, Currency.USD, 5600)
{
    Reference = "ORD-090857",
    Capture = false,
    ThreeDs = true
};

try
{
    var response = await api.Payments.RequestAsync(paymentRequest);

    if (response.IsPending && response.Pending.RequiresRedirect())
    {
        return Redirect(response.Pending.GetRedirectLink().Href);
    }

    if (response.Payment.Approved)
        return PaymentSucessful(response.Payment);

    return PaymentDeclined(response.Payment);
}
catch (CheckoutValidationException validationEx)
{
    return ValidationError(validationEx.Error);
}
catch (CheckoutApiException apiEx)
{
    Log.Error("Payment request failed with status code {HttpStatusCode}", apiEx.HttpStatusCode);
    throw;
}