import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.oauth_scopes import OAuthScopes
from checkout_sdk.sessions.sessions import BrowserSession, ThreeDsMethodCompletion

# OAuth
api = CheckoutSdk.builder() \\
    .oauth() \\
    .client_credentials('client_id', 'client_secret') \\
    .environment(Environment.sandbox()) \\
    .scopes([OAuthScopes.SESSIONS_APP, OAuthScopes.SESSIONS_BROWSER]) \\
    .build()

request = BrowserSession()
request.accept_header = 'Accept:  *.*, q=0.1'
request.java_enabled = True
request.language = 'FR-fr'
request.color_depth = '16'
request.screen_width = '1920'
request.screen_height = '1080'
request.timezone = '60'
request.user_agent = 'Mozilla/5.0 (Windows NT 10.0 Win64 x64) AppleWebKit/537.36 (KHTML, like Gecko) ' \\\\
                     'Chrome/69.0.3497.100 Safari/537.36 '
request.three_ds_method_completion = ThreeDsMethodCompletion.Y
request.ip_address = '1.12.123.255'

try:
    # Optional: session_secret instead of OAuth
    response = api.sessions.update_session('session_id', request)
except CheckoutApiException as err:
    # API error
    request_id = err.request_id
    status_code = err.http_status_code
    error_details = err.error_details
    http_response = err.http_response
except CheckoutArgumentException as err:
    # Bad arguments

except CheckoutAuthorizationException as err:
    # Invalid authorization