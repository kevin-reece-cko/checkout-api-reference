import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.environment import Environment
from checkout_sdk.events.events import RetrieveEventsRequest
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException

api = CheckoutSdk.builder() \\
    .previous() \\
    .secret_key('secret_key') \\
    .environment(Environment.sandbox()) \\
    .build()
    # or Environment.production()

request = RetrieveEventsRequest()
request.payment_id = "pay_ok2ynq6ubn3ufmo6jsdfmdvy5q"
request.charge_id = "charge_FC1919EE547L23CC6BE1"
request.track_id = "TRK12345"
request.reference = "ORD-5023-4E89"
request.skip = "0"
request.limit = "5"
request.from_ = "2020-11-07T04:00:00Z"
request.to = "2020-11-07T08:15:00Z"

try:
    response = api.events.retrieve_events(request)
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