// For more information please refer to https://github.com/checkout/checkout-sdk-javaç
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.common.AccountHolderType;
import com.checkout.common.CountryCode;
import com.checkout.common.Currency;
import com.checkout.instruments.get.BankAccountFieldQuery;
import com.checkout.instruments.get.BankAccountFieldResponse;
import com.checkout.instruments.get.PaymentNetwork;

// OAuth
CheckoutApi api = CheckoutSdk.builder()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .scopes(OAuthScope.PAYOUTS_BANK_DETAILS) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

BankAccountFieldQuery query = BankAccountFieldQuery.builder()
    .accountHolderType(AccountHolderType.INDIVIDUAL)
    .paymentNetwork(PaymentNetwork.LOCAL)
    .build();

try {
    BankAccountFieldResponse response = api.instrumentsClient().getBankAccountFieldFormatting(CountryCode.GB, Currency.GBP, query).get();
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
