// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Instruments;

ICheckoutApi api = CheckoutSdk.DefaultSdk().StaticKeys()
    .PublicKey("public_key")
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

CreateInstrumentRequest request = new CreateInstrumentRequest
{
    Token = "tok_asoto22g2fsu7prwomy12sgfsa",
    AccountHolder = new InstrumentAccountHolder()
    {
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
    Customer = new InstrumentCustomerRequest()
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
    CreateInstrumentResponse response = api.InstrumentsClient().Create(request).Result;
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