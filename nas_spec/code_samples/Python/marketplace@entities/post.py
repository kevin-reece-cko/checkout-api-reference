# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.common.common import Phone, Address
from checkout_sdk.common.enums import Country
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.four.oauth_scopes import OAuthScopes
from checkout_sdk.marketplace.marketplace import OnboardEntityRequest, ContactDetails, Profile, Individual, DateOfBirth, \\
    Identification

# OAuth
api = checkout_sdk.OAuthSdk() \\
    .client_credentials('client_id', 'client_secret') \\
    .environment(Environment.sandbox()) \\
    .scopes([OAuthScopes.MARKETPLACE]) \\
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

request = OnboardEntityRequest()
request.reference = 'reference'
request.contact_details = ContactDetails()
request.contact_details.phone = phone
request.profile = Profile()
request.profile.urls = ['https://docs.checkout.com/url']
request.profile.mccs = ['0742']
request.individual = Individual()
request.individual.first_name = 'First'
request.individual.last_name = 'Last'
request.individual.trading_name = "Batman's Super Hero Masks"
request.individual.registered_address = address
request.individual.national_tax_id = 'TAX123456'
request.individual.date_of_birth = DateOfBirth()
request.individual.date_of_birth.day = 5
request.individual.date_of_birth.month = 6
request.individual.date_of_birth.year = 1996
request.individual.identification = Identification()
request.individual.identification.national_id_number = 'AB123456C'

try:
    response = api.marketplace.create_entity(request)
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
