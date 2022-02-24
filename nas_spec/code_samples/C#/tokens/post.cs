// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Tokens;

Four.ICheckoutApi api = CheckoutSdk.FourSdk().StaticKeys()
    .PublicKey("public_key")
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

CardTokenRequest request = new CardTokenRequest
{
    Number = "4543474002249996",
    ExpiryMonth = 10,
    ExpiryYear = 2027,
    Name = "FirstName LastName",
    Cvv = "123",
    BillingAddress = new Address()
    {
        AddressLine1 = "Checkout.com",
        AddressLine2 = "90 Tottenham Court Road",
        City = "London",
        State = "London",
        Zip = "W1T 4TJ",
        Country = CountryCode.GB
    },
    Phone = new Phone()
    {
        Number = "4155552671",
        CountryCode = "1"
    }
};

try
{
    CardTokenResponse response = api.TokensClient().Request(request).Result;
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