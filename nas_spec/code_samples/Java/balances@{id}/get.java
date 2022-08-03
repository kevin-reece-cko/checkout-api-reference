// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.balances.BalancesQuery;
import com.checkout.balances.BalancesResponse;
import com.checkout.common.Currency;

// OAuth
CheckoutApi api = CheckoutSdk.builder()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .scopes(OAuthScope.BALANCES) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

BalancesQuery query = BalancesQuery.builder()
    .query("currency:" + Currency.EUR)
    .build();

try {
    BalancesResponse response = api.balancesClient().retrieveEntityBalances("balance_id", query).get();
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
