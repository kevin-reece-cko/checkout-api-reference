# please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.four.oauth_scopes import OAuthScopes

# SDK instantiation for OAuth
api = checkout_sdk.OAuthSdk() \\
    .client_credentials('client_id', 'client_secret') \\
    .scopes([OAuthScopes.GATEWAY, OAuthScopes.VAULT, OAuthScopes.PAYOUTS_BANK_DETAILS,
             OAuthScopes.SESSIONS_APP, OAuthScopes.SESSIONS_BROWSER, OAuthScopes.FX, OAuthScopes.MARKETPLACE,
             OAuthScopes.FILES, OAuthScopes.TRANSFERS, OAuthScopes.BALANCES_VIEW]) \\
    .build()