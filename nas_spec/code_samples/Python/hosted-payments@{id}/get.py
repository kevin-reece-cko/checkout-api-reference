# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException

api = CheckoutSdk.builder() \\
    .secret_key('secret_key') \\
    .environment(Environment.sandbox()) \\
    .build()
    # or Environment.production()

try:
    response = api.hosted_payments.get_hosted_payments_page_details('hosted_payment_id')
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
