# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.accounts.accounts import AccountsPaymentInstrument, AccountsAccountHolder, AccountHolderType, InstrumentDocument
from checkout_sdk.common.common import Phone, Address
from checkout_sdk.common.enums import Country, Currency
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.oauth_scopes import OAuthScopes

# OAuth
api = CheckoutSdk.builder() \\
    .oauth() \\
    .client_credentials('client_id', 'client_secret') \\
    .environment(Environment.sandbox()) \\
    .scopes([OAuthScopes.ACCOUNTS]) \\
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

account_holder = AccountsAccountHolder()
account_holder.type = AccountHolderType.INDIVIDUAL
account_holder.first_name = "Peter"
account_holder.last_name = "Parker"
account_holder.phone = phone
account_holder.billing_address = address

document = InstrumentDocument()
document.type = "bank_statement"
document.file_id = "file_id"

accounts_payment_instrument = AccountsPaymentInstrument()
accounts_payment_instrument.label = "Peter's Personal Account"
accounts_payment_instrument.account_number = "12345678"
accounts_payment_instrument.bank_code = "050389"
accounts_payment_instrument.currency = Currency.GBP
accounts_payment_instrument.country = Country.GB
accounts_payment_instrument.account_holder = account_holder
accounts_payment_instrument.document = document

try:
    response = api.accounts.create_payment_instrument('entity_id', accounts_payment_instrument)
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