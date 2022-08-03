// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Accounts;
using Checkout.Common;

ICheckoutApi api = CheckoutSdk.Builder().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(OAuthScope.Accounts)
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

AccountsPaymentInstrument request = new AccountsPaymentInstrument
{
    Label = "Peter's Personal Account",
    AccountNumber = "12345678",
    BankCode = "050389",
    Currency = Currency.GBP,
    Country = CountryCode.GB,
    AccountHolder = new AccountsAccountHolder
    {
        FirstName = "FirstName",
        LastName = "LastName",
        BillingAddress = new Address
        {
            AddressLine1 = "Checkout.com",
            AddressLine2 = "90 Tottenham Court Road",
            City = "London",
            State = "London",
            Zip = "W1T 4TJ",
            Country = CountryCode.GB
        }
    },
    Document = new InstrumentDocument
    {
        Type = "bank_statement", FileId = "file_wxglze3wwywujg4nna5fb7ldli"
    }
};

try
{
    await api.AccountsClient().CreatePaymentInstrument("entity_id", request);
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