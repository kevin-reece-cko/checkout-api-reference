// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Common.Four;
using Checkout.Instruments.Four.Get;

Four.ICheckoutApi api = CheckoutSdk.FourSdk().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(FourOAuthScope.PayoutsBankDetails)
    .Environment(Environment.Sandbox)
    .FilesEnvironment(Environment.Sandbox)
    .Build();

BankAccountFieldQuery request = new BankAccountFieldQuery
{
    AccountHolderType = AccountHolderType.Individual, 
    PaymentNetwork = PaymentNetwork.Local
};

try
{
    BankAccountFieldResponse response = api.InstrumentsClient().GetBankAccountFieldFormatting(CountryCode.GB, Currency.GBP, request).Result;
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