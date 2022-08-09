// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Payments.Request;
using Checkout.Payments.Response;

//API keys
ICheckoutApi api = CheckoutSdk.Builder().StaticKeys()
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

//OAuth
ICheckoutApi api = CheckoutSdk.Builder().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(OAuthScope.Gateway)
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

AuthorizationRequest authorizationRequest = new AuthorizationRequest
{
    Amount = 100, Reference = "payment_reference"
};

try
{
    // Optional: idempotencyKey as a third parameter for idempotent requests
    AuthorizationResponse response = await api.PaymentsClient().IncrementPaymentAuthorization("payment_id", authorizationRequest);
}
catch (CheckoutApiException e)
{
    // API error
    string requestId = e.RequestId;
    var statusCode = e.HttpStatusCode;
    IDictionary<string, object> errorDetails = e.ErrorDetails;
}
catch (CheckoutArgumentException e)
{
    // Bad arguments
}
catch (CheckoutAuthorizationException e)
{
    // Invalid authorization
}
