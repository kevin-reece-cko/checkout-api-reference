# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.common.common import Address, Phone
from checkout_sdk.common.common import CustomerRequest, Product
from checkout_sdk.common.enums import Country, Currency, PaymentSourceType
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.payments.hosted.hosted_payments import HostedPaymentsSessionRequest
from checkout_sdk.payments.payments import BillingInformation, PaymentRecipient, ThreeDsRequest, ProcessingSettings, \\
    RiskRequest, ShippingDetails

api = checkout_sdk.DefaultSdk() \\
    .secret_key('secret_key') \\
    .public_key('public_key') \\
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

customer_request = CustomerRequest()
customer_request.email = 'email@docs.checkout.com'
customer_request.name = 'Name'

billing_information = BillingInformation()
billing_information.address = address
billing_information.phone = phone

shipping_details = ShippingDetails()
shipping_details.address = address
shipping_details.phone = phone

recipient = PaymentRecipient()
recipient.account_number = '123456789'
recipient.country = Country.ES
recipient.dob = '1985-05-18'
recipient.first_name = 'First'
recipient.last_name = 'Last'
recipient.zip = '12345'

product = Product()
product.name = 'Name'
product.quantity = 1
product.price = 10

three_ds_request = ThreeDsRequest()
three_ds_request.enabled = True
three_ds_request.attempt_n3d = False

processing_settings = ProcessingSettings()
processing_settings.aft = True

risk_request = RiskRequest()
risk_request.enabled = True

request = HostedPaymentsSessionRequest()
request.amount = 10
request.reference = 'reference'
request.currency = Currency.GBP
request.description = 'Payment for Gold Necklace'
request.customer = customer_request
request.shipping = shipping_details
request.billing = billing_information
request.recipient = recipient
request.processing = processing_settings
request.products = [product]
request.risk = risk_request
request.success_url = 'https://docs.checkout.com/payments/success'
request.failure_url = 'https://docs.checkout.com/payments/failure'
request.cancel_url = 'https://docs.checkout.com/payments/cancel'
request.locale = 'en-GB'
request.three_ds = three_ds_request
request.capture = True
request.allow_payment_methods = [PaymentSourceType.CARD, PaymentSourceType.KLARNA]

try:
    response = api.hosted_payments.create_hosted_payments_page_session(request)
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
