# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.common.common import Address, Phone
from checkout_sdk.common.enums import Country, Currency
from checkout_sdk.payments.payments_previous import RequestCardSource, PaymentRequest
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException

api = CheckoutSdk.builder() \\
    .previous() \\
    .secret_key('secret_key') \\
    .environment(Environment.sandbox()) \\
    .build()
    # or Environment.production()

address = Address()
address.address_line1 = 'CheckoutSdk.com'
address.address_line2 = '90 Tottenham Court Road'
address.city = 'London'
address.state = 'London'
address.zip = 'W1T 4TJ'
address.country = Country.GB

phone = Phone()
phone.country_code = '44'
phone.number = '4155552671'

request_card_source = RequestCardSource()
request_card_source.number = 'number'
request_card_source.expiry_month = 10
request_card_source.expiry_year = 2027
request_card_source.cvv = 123
request_card_source.name = 'Name'
request_card_source.billing_address = address
request_card_source.phone = phone

payment_request = PaymentRequest()
payment_request.source = request_card_source

payment_request.reference = 'reference'
payment_request.amount = 10
payment_request.currency = Currency.GBP
payment_request.capture = False

try:
    response = api.payments.request_payment(payment_request) # or 'request_payout'
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
