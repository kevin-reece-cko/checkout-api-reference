# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.common.enums import Currency
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.forex.forex import QuoteRequest
from checkout_sdk.oauth_scopes import OAuthScopes

# OAuth
api = CheckoutSdk.builder() \\
    .oauth() \\
    .client_credentials('client_id', 'client_secret') \\
    .environment(Environment.sandbox()) \\
    .scopes([OAuthScopes.FX]) \\
    .build()

quote_request = QuoteRequest()
quote_request.source_currency = Currency.GBP
quote_request.source_amount = 10
quote_request.destination_currency = Currency.USD
quote_request.process_channel_id = 'pc_abcdefghijklmnopqrstuvwxyz'

try:
    response = api.forex.request_quote(quote_request)
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
