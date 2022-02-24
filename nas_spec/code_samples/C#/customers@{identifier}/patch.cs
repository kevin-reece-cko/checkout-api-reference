// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using CustomerRequest = Checkout.Customers.Four.CustomerRequest;

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
    .Scopes(FourOAuthScope.Vault)
    .Environment(Environment.Sandbox)
    .FilesEnvironment(Environment.Sandbox)
    .Build();

CustomerRequest request = new CustomerRequest
{
    Email = "email@docs.checkout.com",
    Name = "FirstName LastName",
    Phone = new Phone
    {
        CountryCode = "1",
        Number = "4155552671"
    },
    Metadata = new Dictionary<string, object>()
    {
        {"coupon_code", "NY2018"},
        {"partner_id", "123989"}
    }
};

try
{
    await api.CustomersClient().Update("customer_id", request);
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