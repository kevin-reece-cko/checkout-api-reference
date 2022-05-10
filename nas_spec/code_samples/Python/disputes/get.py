# For more information please refer to https://github.com/checkout/checkout-sdk-python
from datetime import timezone, datetime

from dateutil.relativedelta import relativedelta

import checkout_sdk
from checkout_sdk.disputes.disputes import DisputesQueryFilter
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.four.oauth_scopes import OAuthScopes

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
    .scopes([OAuthScopes.DISPUTES]) \\
    .build()

query = DisputesQueryFilter()
now = datetime.now(timezone.utc)
query.from_ = now - relativedelta(months=6)
query.to = now
query.payment_arn = 'payment_arn'
query.payment_reference = 'payment_reference'
query.limit = 10
query.skip = 5

try:
    response = api.disputes.query(query)
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
