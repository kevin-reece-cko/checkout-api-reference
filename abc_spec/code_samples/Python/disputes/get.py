# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.disputes.disputes import DisputesQueryFilter
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from datetime import datetime, timezone
from dateutil.relativedelta import relativedelta

api = CheckoutSdk.builder() \\
    .previous() \\
    .secret_key('secret_key') \\
    .environment(Environment.sandbox()) \\
    .build()
    # or Environment.production()

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
