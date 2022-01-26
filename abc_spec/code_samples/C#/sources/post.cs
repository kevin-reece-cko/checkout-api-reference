// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Sources;

ICheckoutApi api = CheckoutSdk.DefaultSdk().StaticKeys()
    .PublicKey("public_key")
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

SepaSourceRequest request = new SepaSourceRequest()
{
    Reference = "reference",
    Phone = new Phone()
    {
        Number = "4155552671",
        CountryCode = "1"
    },
    Customer = new CustomerRequest()
    {
        Id = "cus_y3oqhf46pyzuxjbcn2giaqnb44",
        Email = "email@docs.checkout.com",
        Name = "FirstName LastName"
    },
    BillingAddress = new Address()
    {
        AddressLine1 = "Checkout.com",
        AddressLine2 = "90 Tottenham Court Road",
        City = "London",
        State = "London",
        Zip = "W1T 4TJ",
        Country = CountryCode.GB
    },
    SourceData = new SourceData()
    {
        FirstName = "FirstName",
        LastName = "LastName",
        AccountIban = "DE25100100101234567893",
        Bic = "PBNKDEFFXXX",
        BillingDescriptor = "ExampleCompany.com",
        MandateType = MandateType.Recurring
    }
};

try
{
    SepaSourceResponse response = api.SourcesClient().CreateSepaSource(request).Result;
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