# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.common.enums import Country, Currency
from checkout_sdk.common.enums_four import AccountHolderType
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.four.oauth_scopes import OAuthScopes

# API Keys
from checkout_sdk.instruments.instruments_four import BankAccountFieldQuery, PaymentNetwork

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
    .scopes([OAuthScopes.PAYOUTS_BANK_DETAILS]) \\
    .build()

query = BankAccountFieldQuery()
query.account_holder_type = AccountHolderType.INDIVIDUAL
query.payment_network = PaymentNetwork.LOCAL

try:
    response = api.instruments.get_bank_account_field_formatting(Country.GB, Currency.GBP, query)
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
