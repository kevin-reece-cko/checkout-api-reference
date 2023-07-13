# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.instruments.instruments import BankAccountFieldQuery, AccountHolderType, PaymentNetwork
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.common.enums import Country, Currency
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.oauth_scopes import OAuthScopes

# OAuth
api = CheckoutSdk.builder() \\
    .oauth() \\
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
