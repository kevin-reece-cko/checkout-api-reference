// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Payments;
using Checkout.Payments.Request;
using Checkout.Payments.Request.Source;
using Checkout.Payments.Response;

ICheckoutApi api = CheckoutSdk.DefaultSdk().StaticKeys()
    .PublicKey("public_key")
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

PaymentRequest request = new PaymentRequest
{
    Source = new RequestTokenSource()
    {
        Token = "tok_4gzeau5o2uqubbk6fufs3m7p54"
    },
    Amount = 10,
    Currency = Currency.USD,
    PaymentType = PaymentType.Recurring,
    Reference = "reference",
    Description = "Set of 3 masks",
    Capture = true,
    CaptureOn = new DateTime(),
    Customer = new CustomerRequest()
    {
        Id = "cus_udst2tfldj6upmye2reztkmm4i",
        Email = "email@docs.checkout.com",
        Name = "FirstName LastName"
    },
    BillingDescriptor = new BillingDescriptor()
    {
        Name = "SUPERHEROES.COM",
        City = "GOTHAM"
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
    ThreeDs = new ThreeDsRequest()
    {
        Enabled = true,
        AttemptN3D = true,
        Eci = "05",
        Cryptogram = "AgAAAAAAAIR8CQrXcIhbQAAAAAA=",
        Xid = "MDAwMDAwMDAwMDAwMDAwMzIyNzY=",
        Version = "2.0.1"
    },
    PreviousPaymentId = "pay_fun26akvvjjerahhctaq2uzhu4",
    Risk = new RiskRequest()
    {
        Enabled = false
    },
    SuccessUrl = "https://example.com/payments/success",
    FailureUrl = "https://example.com/payments/failure",
    PaymentIp = "192.168.0.1",
    Recipient = new PaymentRecipient()
    {
        DateOfBirth = "1985-05-15",
        AccountNumber = "5555554444",
        Zip = "WIT",
        FirstName = "FirstName",
        LastName = "LastName",
        Country = CountryCode.GB
    },
    Metadata = new Dictionary<string, object>()
    {
        {"coupon_code", "NY2018"},
        {"partner_id", 123989}
    }
};

try
{
    PaymentResponse response = api.PaymentsClient().RequestPayment(request).Result;
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