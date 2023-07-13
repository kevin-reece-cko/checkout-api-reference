import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.common.common import Phone
from checkout_sdk.common.enums import Country, Currency
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.oauth_scopes import OAuthScopes
from checkout_sdk.sessions.sessions import SessionAddress, SessionCardSource, SessionMarketplaceData, SessionsBillingDescriptor, \\
                                        NonHostedCompletionInfo, BrowserSession, ThreeDsMethodCompletion, SessionRequest, \\
                                        Category, ChallengeIndicator, TransactionType

# OAuth
api = CheckoutSdk.builder() \\
    .oauth() \\
    .client_credentials('client_id', 'client_secret') \\
    .environment(Environment.sandbox()) \\
    .scopes([OAuthScopes.SESSIONS_APP, OAuthScopes.SESSIONS_BROWSER]) \\
    .build()

phone = Phone()
phone.country_code = '44'
phone.number = '4155552671'

billing_address = SessionAddress()
billing_address.address_line1 = 'CheckoutSdk.com'
billing_address.address_line2 = '90 Tottenham Court Road'
billing_address.city = 'London'
billing_address.state = 'ENG'
billing_address.country = Country.GB

session_card_source = SessionCardSource()
session_card_source.billing_address = billing_address
session_card_source.number = 'number'
session_card_source.expiry_month = 10
session_card_source.expiry_year = 2027
session_card_source.name = 'Name'
session_card_source.email = 'email@docs.checkout.com'
session_card_source.home_phone = phone
session_card_source.work_phone = phone
session_card_source.mobile_phone = phone

shipping_address = SessionAddress()
shipping_address.address_line1 = 'CheckoutSdk.com'
shipping_address.address_line2 = 'ABC building'
shipping_address.address_line3 = '14 Wells Mews'
shipping_address.city = 'London'
shipping_address.state = 'ENG'
shipping_address.zip = 'W1T 4TJ'
shipping_address.country = Country.GB

marketplace_data = SessionMarketplaceData()
marketplace_data.sub_entity_id = 'ent_ocw5i74vowfg2edpy66izhts2u'

billing_descriptor = SessionsBillingDescriptor()
billing_descriptor.name = 'Name'

non_hosted_completion_info = NonHostedCompletionInfo()
non_hosted_completion_info.callback_url = 'https://docs.checkout.com/callback'

browser_session = BrowserSession()
browser_session.accept_header = 'Accept:  *.*, q=0.1'
browser_session.java_enabled = True
browser_session.language = 'FR-fr'
browser_session.color_depth = '16'
browser_session.screen_width = '1920'
browser_session.screen_height = '1080'
browser_session.timezone = '60'
browser_session.user_agent = 'Mozilla/5.0 (Windows NT 10.0 Win64 x64) AppleWebKit/537.36 (KHTML, like Gecko) ' \\\\
                             'Chrome/69.0.3497.100 Safari/537.36 '
browser_session.three_ds_method_completion = ThreeDsMethodCompletion.Y
browser_session.ip_address = '1.12.123.255'

request = SessionRequest()
request.source = session_card_source
request.amount = 10
request.currency = Currency.USD
request.processing_channel_id = 'pc_5jp2az55l3cuths25t5p3xhwru'
request.marketplace = marketplace_data
request.authentication_category = Category.PAYMENT
request.challenge_indicator = ChallengeIndicator.NO_PREFERENCE
request.billing_descriptor = billing_descriptor
request.reference = 'ORD-5023-4E89'
request.transaction_type = TransactionType.GOODS_SERVICE
request.shipping_address = shipping_address
request.completion = non_hosted_completion_info
request.channel_data = browser_session

try:
    response = api.sessions.request_session(request)
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