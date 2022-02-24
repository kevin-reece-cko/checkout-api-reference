// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Marketplace;
using Phone = Checkout.Marketplace.Phone;

Four.ICheckoutApi api = CheckoutSdk.FourSdk().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(FourOAuthScope.Marketplace)
    .Environment(Environment.Sandbox)
    .FilesEnvironment(Environment.Sandbox)
    .Build();

OnboardEntityRequest request = new OnboardEntityRequest
{
    Reference = "reference",
    ContactDetails = new ContactDetails()
    {
        Phone = new Phone()
        {
            Number = "2345678910"
        }
    },
    Profile = new Profile()
    {
        Urls = new List<string>()
        {
            "https://www.superheroexample.com"
        },
        Mccs = new List<string>()
        {
            "5669"
        }
    },
    Individual = new Individual()
    {
        FirstName = "FirstName",
        LastName = "LastName",
        TradingName = "John's Super Hero Masks",
        RegisteredAddress = new Address()
        {
            AddressLine1 = "Checkout.com",
            AddressLine2 = "90 Tottenham Court Road",
            City = "London",
            State = "London",
            Zip = "W1T 4TJ",
            Country = CountryCode.GB
        },
        NationalTaxId = "TAX123456",
        DateOfBirth = new DateOfBirth()
        {
            Day = 5,
            Month = 6,
            Year = 1995
        },
        Identification = new Identification()
        {
            NationalIdNumber = "AB123456C"
        }
    }
};

try
{
    OnboardEntityResponse response = api.MarketplaceClient().UpdateEntity("entity_id", request).Result;
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