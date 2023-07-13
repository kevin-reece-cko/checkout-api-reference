# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.common.common import Phone, Address
from checkout_sdk.common.enums import Country
from checkout_sdk.sources.sources import SourceData, SepaSourceRequest, MandateType
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

source_data = SourceData()
source_data.first_name = 'First'
source_data.last_name = 'Last'
source_data.account_iban = 'iban'
source_data.bic = 'PBNKDEFFXXX'
source_data.billing_descriptor = 'descriptor'
source_data.mandate_type = MandateType.SINGLE

sepa_source_request = SepaSourceRequest()
sepa_source_request.billing_address = address
sepa_source_request.reference = 'reference'
sepa_source_request.phone = phone
sepa_source_request.source_data = source_data

try:
    response = api.sources.create_sepa_source(sepa_source_request)
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