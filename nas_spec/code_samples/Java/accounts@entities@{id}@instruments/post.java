// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.EmptyResponse;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.accounts.AccountsPaymentInstrument;
import com.checkout.accounts.InstrumentDocument;
import com.checkout.common.AccountType;
import com.checkout.common.Address;
import com.checkout.common.BankDetails;
import com.checkout.common.CountryCode;
import com.checkout.common.Currency;

// OAuth
CheckoutApi api = CheckoutSdk.builder()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .scopes(OAuthScope.ACCOUNTS) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

AccountsPaymentInstrument marketplacePaymentInstrument = AccountsPaymentInstrument.builder()
    .accountNumber("123456789")
    .accountType(AccountType.CASH)
    .bank(BankDetails.builder().name("bank_name").address(Address.builder().build()).build())
    .bankCode("bank_code")
    .bban("bban")
    .branchCode("123")
    .country(CountryCode.GB)
    .currency(Currency.GBP)
    .document(new InstrumentDocument("document", "file"))
    .iban("iban")
    .label("mkt-1")
    .swiftBic("BIC")
    .build();

try {
    EmptyResponse response = api.accountsClient().createPaymentInstrument(marketplacePaymentInstrument, "entity_id").get();
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
