# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.common.common import Phone, Address, CustomerRequest
from checkout_sdk.common.enums import Country, Currency
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.four.oauth_scopes import OAuthScopes
from checkout_sdk.payments.payments_four import PaymentRequestCardSource, Identification, IdentificationType, \\
    PaymentIndividualSender, PaymentRequest

# API Keys
api = checkout_sdk.FourSdk() \\
    .secret_key('secret_key') \\
    .public_key('public_key') \\
    .environment(Environment.sandbox()) \\
    .build()
# or Environment.production()

# OAuth
api = checkout_sdk.OAuthSdk() \\
    .client_credentials('client_id', 'client_secret') \\
    .environment(Environment.sandbox()) \\
    .scopes([OAuthScopes.GATEWAY]) \\
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

request_card_source = PaymentRequestCardSource()
request_card_source.number = 'number'
request_card_source.expiry_month = 10
request_card_source.expiry_year = 2027
request_card_source.cvv = 123
request_card_source.name = 'Name'
request_card_source.billing_address = address
request_card_source.phone = phone

customer_request = CustomerRequest()
customer_request.email = 'email@docs.checkout.com'
customer_request.name = 'Name'

identification = Identification()
identification.issuing_country = Country.GT
identification.number = '1234'
identification.type = IdentificationType.DRIVING_LICENSE

payment_individual_sender = PaymentIndividualSender()
payment_individual_sender.first_name = 'First'
payment_individual_sender.last_name = 'Last'
payment_individual_sender.address = address
payment_individual_sender.identification = identification

request = PaymentRequest()
request.source = request_card_source
request.reference = 'reference'
request.amount = 10
request.currency = Currency.USD
request.capture = False
request.customer = customer_request
request.sender = payment_individual_sender

try:
    response = api.payments.request_payment(request)
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
