// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Payments;
using Checkout.Payments.Four.Request;
using Checkout.Payments.Four.Request.Source;
using Checkout.Payments.Four.Response;

//API keys
Four.ICheckoutApi api = CheckoutSdk.FourSdk().StaticKeys()
    .PublicKey("public_key")
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

//OAuth
Four.ICheckoutApi api = CheckoutSdk.FourSdk().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(FourOAuthScope.GatewayPayment)
    .Environment(Environment.Sandbox)
    .FilesEnvironment(Environment.Sandbox)
    .Build();

AuthorizationRequest authorizationRequest = new AuthorizationRequest
{
    Amount = 100, Reference = "payment_reference"
};

try
{
    // Optional: idempotencyKey as a third parameter for idempotent requests
    AuthorizationResponse response = api.PaymentsClient().IncrementPaymentAuthorization("payment_id", authorizationRequest).Result;
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
