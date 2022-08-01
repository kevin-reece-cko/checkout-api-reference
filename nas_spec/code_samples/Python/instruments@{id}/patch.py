# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.common.common import Phone, Address, AccountHolder, UpdateCustomerRequest
from checkout_sdk.common.enums import Country
from checkout_sdk.environment import Environment
from checkout_sdk.instruments.instruments import UpdateCardInstrumentRequest
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

address = Address()
address.address_line1 = 'CheckoutSdk.com'
address.address_line2 = '90 Tottenham Court Road'
address.city = 'London'
address.state = 'London'
address.zip = 'W1T 4TJ'
address.country = Country.GB

account_holder = AccountHolder()
account_holder.first_name = "First"
account_holder.last_name = "Last"
account_holder.phone = phone
account_holder.billing_address = address

update_customer_request = UpdateCustomerRequest()
update_customer_request.id = "customer_id"
update_customer_request.default = True

request = UpdateCardInstrumentRequest()
request.name = 'New Name'
request.expiry_year = 2027
request.expiry_month = 10
request.account_holder = account_holder
request.customer = update_customer_request

try:
    response = api.instruments.update('instrument_id', request)
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