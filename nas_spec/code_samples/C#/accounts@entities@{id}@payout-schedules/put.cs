// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Accounts.Payout;
using Checkout.Accounts.Payout.Request;
using Checkout.Common;

ICheckoutApi api = CheckoutSdk.Builder().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(OAuthScope.Accounts)
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

UpdateScheduleRequest scheduleRequest = new UpdateScheduleRequest
{
    Enabled = true,
    Threshold = 1000,
    Recurrence =
        new ScheduleFrequencyWeeklyRequest {ByDay = new[] {DaySchedule.Sunday, DaySchedule.Monday}}
};

try
{
    EmptyResponse emptyResponse = await api.AccountsClient().UpdatePayoutSchedule("entity_id", Currency.USD, scheduleRequest);
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