# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.common.common import Phone, AccountHolder
from checkout_sdk.environment import Environment
from checkout_sdk.instruments.instruments import CreateTokenInstrumentRequest, CreateCustomerInstrumentRequest
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.oauth_scopes import OAuthScopes

# API Keys
api = CheckoutSdk.builder() \\
    .secret_key('secret_key') \\
    .environment(Environment.sandbox()) \\
    .build()
    # or Environment.production()

# OAuth
api = CheckoutSdk.builder() \\
    .oauth() \\
    .client_credentials('client_id', 'client_secret') \\
    .environment(Environment.sandbox()) \\
    .scopes([OAuthScopes.VAULT_INSTRUMENTS]) \\
    .build()

phone = Phone()
phone.country_code = '44'
phone.number = '4155552671'

customer = CreateCustomerInstrumentRequest()
customer.email = 'email@docs.checkout.com'
customer.name = 'Name'
customer.default = True
customer.phone = phone

account_holder = AccountHolder()
account_holder.first_name = 'First'
account_holder.last_name = 'Last'
account_holder.phone = phone

request = CreateTokenInstrumentRequest()
request.token = 'token'
request.account_holder = account_holder
request.customer = customer

try:
    response = api.instruments.create(request)
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
