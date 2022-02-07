// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.common.Address;
import com.checkout.common.CountryCode;
import com.checkout.common.Currency;
import com.checkout.common.four.AccountType;
import com.checkout.common.four.BankDetails;
import com.checkout.four.CheckoutApi;
import com.checkout.marketplace.InstrumentDocument;
import com.checkout.marketplace.MarketplacePaymentInstrument;

CheckoutApi api = CheckoutSdk.fourSdk()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .scopes(FourOAuthScope.MARKETPLACE) // more scopes available
    .build();

MarketplacePaymentInstrument marketplacePaymentInstrument = MarketplacePaymentInstrument.builder()
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
    api.marketplaceClient().createPaymentInstrument(marketplacePaymentInstrument, "entity_id").get();
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
