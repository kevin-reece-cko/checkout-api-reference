# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.oauth_scopes import OAuthScopes
from checkout_sdk.sessions.sessions import ThreeDsMethodCompletionRequest, ThreeDsMethodCompletion

# OAuth
api = CheckoutSdk.builder() \\
    .oauth() \\
    .client_credentials('client_id', 'client_secret') \\
    .environment(Environment.sandbox()) \\
    .scopes([OAuthScopes.SESSIONS_APP, OAuthScopes.SESSIONS_BROWSER]) \\
    .build()

request = ThreeDsMethodCompletionRequest()
request.three_ds_method_completion = ThreeDsMethodCompletion.Y

try:
    response = api.sessions.update_3ds_method_completion('session_id', request)
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
