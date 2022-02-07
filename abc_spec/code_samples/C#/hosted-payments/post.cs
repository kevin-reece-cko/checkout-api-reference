// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Payments;
using Checkout.Payments.Hosted;

ICheckoutApi api = CheckoutSdk.DefaultSdk().StaticKeys()
    .PublicKey("public_key")
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

HostedPaymentRequest request = new HostedPaymentRequest()
{
    Amount = 10,
    Currency = Currency.GBP,
    PaymentType = PaymentType.Regular,
    PaymentIp = "192.168.0.1",
    BillingDescriptor = new BillingDescriptor()
    {
        Name = "Name",
        City = "City"
    },
    Reference = "reference",
    Description = "Payment for Gold Necklace",
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
    Risk = new RiskRequest()
    {
        Enabled = false
    },
    SuccessUrl = "https://example.com/payments/success",
    CancelUrl = "https://example.com/payments/cancel",
    FailureUrl = "https://example.com/payments/failure",
    Metadata = new Dictionary<string, object>(),
    Locale = "en-GB",
    ThreeDs = new ThreeDsRequest()
    {
        Enabled = false,
        AttemptN3D = false
    },
    Capture = true,
    CaptureOn = new DateTime()
};

try
{
    HostedPaymentResponse response = api.HostedPaymentsClient().Create(request).Result;
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