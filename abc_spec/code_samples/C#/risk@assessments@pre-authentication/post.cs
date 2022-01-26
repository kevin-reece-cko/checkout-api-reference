// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Risk;
using Checkout.Risk.PreAuthentication;
using Checkout.Risk.source;

ICheckoutApi api = CheckoutSdk.DefaultSdk().StaticKeys()
    .PublicKey("public_key")
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

PreAuthenticationAssessmentRequest request = new PreAuthenticationAssessmentRequest()
{
    Date = DateTime.Now,
    Source = new CardSourcePrism(),
    Customer = new CustomerRequest()
    {
        Name = "FirstName LastName",
        Email = "email@docs.checkout.com",
    },
    Payment = new RiskPayment()
    {
        Psp = "Checkout.com",
        Id = "78453878"
    },
    Shipping = new RiskShippingDetails()
    {
        Address = new Address()
        {
            AddressLine1 = "Checkout.com",
            AddressLine2 = "90 Tottenham Court Road",
            City = "London",
            State = "London",
            Zip = "W1T 4TJ",
            Country = CountryCode.GB
        }
    },
    Reference = "reference",
    Description = "Set of 3 masks",
    Amount = 10,
    Currency = Currency.GBP,
    Device = new Device()
    {
        Ip = "90.197.169.245",
        Location = new Location()
        {
            Latitude = "51.5107",
            Longitude = "0.01313"
        },
        Os = "ISO",
        Type = "Phone",
        Model = "IPHone X",
        Date = DateTime.Now,
        UserAgent =
            "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1",
        Fingerprint = "34304a9e3fg09302"
    },
    Metadata = new Dictionary<string, object>()
    {
        {"VoucherCode", "loyalty_10"},
        {"discountApplied", "10"},
        {"customer_id", "2190EF321"}
    }
};

try
{
    PreAuthenticationAssessmentResponse response =
        api.RiskClient().RequestPreAuthenticationRiskScan(request).Result;
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