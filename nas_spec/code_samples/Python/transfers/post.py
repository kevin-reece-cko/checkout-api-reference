import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.oauth_scopes import OAuthScopes
from checkout_sdk.transfers.transfers import CreateTransferRequest, TransferType, TransferSource, TransferDestination

# OAuth
api = CheckoutSdk.builder() \\
    .oauth() \\
    .client_credentials('client_id', 'client_secret') \\
    .environment(Environment.sandbox()) \\
    .scopes([OAuthScopes.TRANSFERS]) \\
    .build()

source = TransferSource()
source.id = "source_id"
source.amount = 100

destination = TransferDestination()
destination.id = "destination_id"

request = CreateTransferRequest()
request.reference = "superhero1234"
request.transfer_type = TransferType.COMMISSION
request.source = source
request.destination = destination

try:
    # Optional: idempotencyKey as a second parameter for idempotent requests
    response = api.transfers.initiate_transfer_of_funds(request)
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