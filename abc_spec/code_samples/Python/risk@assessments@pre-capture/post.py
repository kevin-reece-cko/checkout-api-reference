# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.common.common import Phone, Address, CustomerRequest
from checkout_sdk.common.enums import Country, Currency
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.risk.risk import RiskRequestTokenSource, RiskPayment, RiskShippingDetails, Location, Device, \\
                                    AuthenticationResult, AuthorizationResult, PreCaptureAssessmentRequest
from datetime import datetime, timezone

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

token_source = RiskRequestTokenSource()
token_source.token = 'token'
token_source.phone = phone
token_source.billing_address = address

customer_request = CustomerRequest()
customer_request.email = 'email@docs.checkout.com'
customer_request.name = 'Name'

risk_payment = RiskPayment()
risk_payment.psp = 'CheckoutSdk.com'
risk_payment.id = '78453878'

shipping_details = RiskShippingDetails()
shipping_details.address = address

location = Location()
location.longitude = '0.1313'
location.latitude = '51.5107'

device = Device()
device.location = location
device.type = 'Phone'
device.os = 'ISO'
device.model = 'iPhone X'
device.date = datetime.now(timezone.utc)
device.user_agent = 'Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, ' \\\\
                    'like Gecko) Version/11.0 Mobile/15A372 Safari/604.1 '
device.fingerprint = '34304a9e3fg09302'

authentication_result = AuthenticationResult()
authentication_result.attempted = True
authentication_result.challenged = True
authentication_result.liability_shifted = True
authentication_result.method = '3ds'
authentication_result.succeeded = True
authentication_result.version = '2.0'

authorization_result = AuthorizationResult()
authorization_result.avs_code = 'Y'
authorization_result.cvv_result = 'N'

request = PreCaptureAssessmentRequest()
request.date = datetime.now(timezone.utc)
request.source = token_source
request.customer = customer_request
request.payment = risk_payment
request.shipping = shipping_details
request.amount = 10
request.currency = Currency.GBP
request.device = device
request.authentication_result = authentication_result
request.authorization_result = authorization_result
request.metadata = {
    'VoucherCode': 'loyalty_10',
    'discountApplied': '10',
    'customer_id': '2190EF321'}

try:
    response = api.risk.request_pre_capture_risk_scan(request)
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
