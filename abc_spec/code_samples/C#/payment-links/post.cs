// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Payments;
using Checkout.Payments.Links;

    ICheckoutApi api = CheckoutSdk.DefaultSdk().StaticKeys()
        .PublicKey("public_key")
        .SecretKey("secret_key")
        .Environment(Environment.Sandbox)
        .HttpClientFactory(new DefaultHttpClientFactory())
        .Build();

    PaymentLinkRequest request = new PaymentLinkRequest
    {
        Amount = 10,
        Currency = Currency.GBP,
        PaymentType = PaymentType.Regular,
        PaymentIp = "192.168.0.1",
        BillingDescriptor = new BillingDescriptor()
        {
            Name = "string",
            City = "string"
        },
        Reference = "reference",
        Description = "Payment for Gold Necklace",
        ExpiresIn = 604800,
        Customer = new CustomerRequest()
        {
            Email = "email@docs.checkout.com",
            Name = "FirstName LastName"
        },
        Shipping = new ShippingDetails()
        {
            Address = new Address()
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
        Billing = new BillingInformation()
        {
            Address = new Address()
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
        Recipient = new PaymentRecipient()
        {
            DateOfBirth = "1985-05-15",
            AccountNumber = "5555554444",
            Zip = "WIT",
            FirstName = "FirstName",
            LastName = "LastName",
            Country = CountryCode.GB
        },
        Processing = new ProcessingSettings()
        {
            Aft = true
        },
        Products = new List<Product>()
        {
            new Product()
            {
                Name = "Gold Necklace",
                Quantity = 1,
                Price = 1000
            }
        },
        Metadata = new Dictionary<string, object>(),
        ThreeDs = new ThreeDsRequest()
        {
            Enabled = false,
            AttemptN3D = false
        },
        Risk = new RiskRequest()
        {
            Enabled = false
        },
        ReturnUrl = "https://example.com/payments/success",
        Locale = "en-GB",
        Capture = true,
        CaptureOn = new DateTime()
    };

try
{
    PaymentLinkResponse response = api.PaymentLinksClient().Create(request).Result;
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