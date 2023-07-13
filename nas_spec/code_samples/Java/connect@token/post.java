// Please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;

// SDK instantiation for OAuth
CheckoutApi api = CheckoutSdk.builder()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .scopes(OAuthScope.FILES, OAuthScope.FLOW, OAuthScope.FX, OAuthScope.GATEWAY,
            OAuthScope.MARKETPLACE, OAuthScope.SESSIONS_APP, OAuthScope.SESSIONS_BROWSER,
            OAuthScope.VAULT, OAuthScope.PAYOUTS_BANK_DETAILS) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();
