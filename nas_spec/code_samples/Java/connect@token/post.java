// Please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.FourOAuthScope;
import com.checkout.four.CheckoutApi;

// SDK instantiation for OAuth
CheckoutApi api = CheckoutSdk.fourSdk()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .scopes(FourOAuthScope.FILES, FourOAuthScope.FLOW, FourOAuthScope.FX, FourOAuthScope.GATEWAY,
        FourOAuthScope.MARKETPLACE, FourOAuthScope.SESSIONS_APP, FourOAuthScope.SESSIONS_BROWSER,
        FourOAuthScope.VAULT, FourOAuthScope.PAYOUTS_BANK_DETAILS) // more scopes available
    .build();
