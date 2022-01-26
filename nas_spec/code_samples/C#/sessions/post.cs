// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Common;
using Checkout.Sessions;
using Checkout.Sessions.Channel;
using Checkout.Sessions.Completion;
using Checkout.Sessions.Source;

Four.ICheckoutApi api = CheckoutSdk.FourSdk().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(FourOAuthScope.Sessions)
    .Environment(Environment.Sandbox)
    .FilesEnvironment(Environment.Sandbox)
    .Build();

SessionRequest request = new SessionRequest
{
    Source = new SessionCardSource()
    {
        BillingAddress = new SessionAddress()
        {
            AddressLine1 = "Checkout.com",
            AddressLine2 = "90 Tottenham Court Road",
            City = "London",
            State = "London",
            Zip = "W1T 4TJ",
            Country = CountryCode.GB
        },
        HomePhone = new Phone()
        {
            Number = "4155552671",
            CountryCode = "1"
        },
        MobilePhone = new Phone()
        {
            Number = "4155552671",
            CountryCode = "1"
        },
        WorkPhone = new Phone()
        {
            Number = "4155552671",
            CountryCode = "1"
        }
    },
    Amount = 10,
    Currency = Currency.USD,
    ProcessingChannelId = "pc_5jp2az55l3cuths25t5p3xhwru",
    Marketplace = new MarketplaceData {SubEntityId = "ent_ocw5i74vowfg2edpy66izhts2u"},
    AuthenticationCategory = Category.Payment,
    ChallengeIndicator = ChallengeIndicatorType.ChallengeRequested,
    BillingDescriptor = new SessionsBillingDescriptor {Name = "SUPERHEROES.COM"},
    Reference = "reference",
    TransactionType = TransactionType.GoodsService,
    ShippingAddress = new SessionAddress(),
    Completion = new NonHostedCompletionInfo {CallbackUrl = "https://merchant.com/callback"},
    ChannelData = new BrowserSession()
    {
        AcceptHeader = "Accept:  *.*, q=0.1",
        JavaEnabled = true,
        Language = "FR-fr",
        ColorDepth = "16",
        ScreenHeight = "1080",
        ScreenWidth = "1920",
        Timezone = "60",
        UserAgent =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36",
        ThreeDsMethodCompletion = ThreeDsMethodCompletion.Y,
        IpAddress = "1.12.123.255"
    }
};

try
{
    SessionResponse response = api.SessionsClient().RequestSession(request).Result;
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