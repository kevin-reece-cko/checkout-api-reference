// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.accounts.payout.schedule.DaySchedule;
import com.checkout.accounts.payout.schedule.request.ScheduleFrequencyWeeklyRequest;
import com.checkout.accounts.payout.schedule.request.UpdateScheduleRequest;
import com.checkout.accounts.payout.schedule.response.VoidResponse;
import com.checkout.common.Currency;

// OAuth
CheckoutApi api = CheckoutSdk.builder()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .scopes(OAuthScope.ACCOUNTS) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

UpdateScheduleRequest request = UpdateScheduleRequest.builder()
    .enabled(true)
    .threshold(1000)
    .recurrence(ScheduleFrequencyWeeklyRequest.builder()
        .byDay(DaySchedule.MONDAY)
        .build())
    .build();

try {
    VoidResponse response = api.accountsClient().updatePayoutSchedule("entity_id", Currency.USD, request).get();
} catch (CheckoutApiException e) {
    // API error
    String requestId = e.getRequestId();
    int statusCode = e.getHttpStatusCode();
    Map<String, Object> errorDetails = e.getErrorDetails();
} catch (CheckoutArgumentException e) {
    // Bad arguments
} catch (CheckoutAuthorizationException e) {
    // Invalid authorization
}
