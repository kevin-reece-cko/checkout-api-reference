// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Marketplace;

Four.ICheckoutApi api = CheckoutSdk.FourSdk().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(FourOAuthScope.Marketplace)
    .Environment(Environment.Sandbox)
    .FilesEnvironment(Environment.Sandbox)
    .Build();

MarketplacePaymentInstrument request = new MarketplacePaymentInstrument()
{
    Label = "Peter's Personal Account",
    AccountNumber = "12345678",
    BankCode = "050389",
    Currency = Currency.GBP,
    Country = CountryCode.GB,
    AccountHolder = new MarketplaceAccountHolder()
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
        }
    },
    Document = new InstrumentDocument()
    {
        Type = "bank_statement",
        FileId = "file_wxglze3wwywujg4nna5fb7ldli"
    }
};

try
{
    await api.MarketplaceClient().CreatePaymentInstrument("entity_id", request);
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