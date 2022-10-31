# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.common.common import Phone
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.instruments.instruments_previous import InstrumentCustomerRequest, CreateInstrumentRequest

api = CheckoutSdk.builder() \\
    .previous() \\
    .secret_key('secret_key') \\
    .environment(Environment.sandbox()) \\
    .build()
    # or Environment.production()

phone = Phone()
phone.country_code = '44'
phone.number = '4155552671'

customer = InstrumentCustomerRequest()
customer.email = 'email@docs.checkout.com'
customer.name = 'Name'
customer.default = True
customer.phone = phone

create_instrument_request = CreateInstrumentRequest()
create_instrument_request.token = 'token'
create_instrument_request.customer = customer

try:
    response = api.instruments.create(create_instrument_request)
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
