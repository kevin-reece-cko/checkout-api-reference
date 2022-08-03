// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Instruments.Get;


ICheckoutApi api = CheckoutSdk.Builder().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(OAuthScope.PayoutsBankDetails)
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

BankAccountFieldQuery request = new BankAccountFieldQuery
{
    AccountHolderType = AccountHolderType.Individual, PaymentNetwork = PaymentNetwork.Local
};

try
{
    BankAccountFieldResponse response = await api.InstrumentsClient()
        .GetBankAccountFieldFormatting(CountryCode.GB, Currency.GBP, request);
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