# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.common.common import Phone, Address
from checkout_sdk.common.enums import Country
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.instruments.instruments_previous import UpdateInstrumentCustomer, InstrumentAccountHolder, UpdateInstrumentRequest

api = CheckoutSdk.builder() \\
    .previous() \\
    .secret_key('secret_key') \\
    .environment(Environment.sandbox()) \\
    .build()
    # or Environment.production()

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

update_instrument_customer = UpdateInstrumentCustomer()
update_instrument_customer.id = 'id'
update_instrument_customer.default = True

account_holder = InstrumentAccountHolder()
account_holder.billing_address = address
account_holder.phone = phone

update_instrument_request = UpdateInstrumentRequest()
update_instrument_request.name = 'New Name'
update_instrument_request.expiry_year = 2027
update_instrument_request.expiry_month = 12
update_instrument_request.customer = update_instrument_customer
update_instrument_request.account_holder = account_holder

try:
    response = api.instruments.update('instrument_id', update_instrument_request)
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
