// For more information please refer to https://github.com/checkout/checkout-sdk-javaç
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.CountryCode;
import com.checkout.common.Currency;
import com.checkout.common.four.AccountHolderType;
import com.checkout.four.CheckoutApi;
import com.checkout.instruments.four.get.BankAccountFieldQuery;
import com.checkout.instruments.four.get.BankAccountFieldResponse;
import com.checkout.instruments.four.get.PaymentNetwork;

CheckoutApi api = CheckoutSdk.fourSdk()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .scopes(FourOAuthScope.PAYOUTS_BANK_DETAILS) // more scopes available
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
