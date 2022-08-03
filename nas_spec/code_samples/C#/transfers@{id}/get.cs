// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Transfers;

ICheckoutApi api = CheckoutSdk.Builder().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(OAuthScope.Transfers)
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

CreateTransferRequest createTransferRequest =
    new CreateTransferRequest
    {
        Source = new TransferSourceRequest {Amount = 100, Id = "entity_source_id"},
        Destination = new TransferDestinationRequest {Id = "entity_destination_id"},
        TransferType = TransferType.Commission
    };

try
{
    TransferDetailsResponse transferDetailsResponse = await api.TransfersClient().RetrieveATransfer("transfer_id");
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