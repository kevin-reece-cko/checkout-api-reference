// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Common.Four;
using Checkout.Instruments.Four.Create;

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
    .Scopes(FourOAuthScope.VaultInstruments)
    .Environment(Environment.Sandbox)
    .FilesEnvironment(Environment.Sandbox)
    .Build();

CreateTokenInstrumentRequest request = new CreateTokenInstrumentRequest
{
    Token = "tok_asoto22g2fsu7prwomy12sgfsa",
    AccountHolder = new AccountHolder()
    {
        FirstName = "FirstName",
        LastName = "LastName",
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
    },
    Customer = new CreateCustomerInstrumentRequest()
    {
        Email = "email@docs.checkout.com",
        Name = "FirstName LastName",
        Phone = new Phone()
        {
            CountryCode = "1",
            Number = "4155552671"
        },
        Default = true
    }
};

try
{
    CreateTokenInstrumentResponse response =
        api.InstrumentsClient().Create<CreateTokenInstrumentResponse>(request).Result;
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