import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.webhooks.webhooks import WebhookRequest, WebhookContentType

api = CheckoutSdk.builder() \\
    .previous() \\
    .secret_key('secret_key') \\
    .environment(Environment.sandbox()) \\
    .build()
# or Environment.production()

request = WebhookRequest()
request.url = "https://example.com/webhooks"
request.active = True
request.content_type = WebhookContentType.JSON
request.event_types = ["payment_approved", "payment_pending", "payment_declined", "payment_expired", "payment_canceled", \\
   "payment_voided", "payment_void_declined", "payment_captured", "payment_capture_declined", "payment_capture_pending", \\
   "payment_refunded", "payment_refund_declined", "payment_refund_pending"]
request.headers = {
    "authorization": "1234"
}

try:
    response = api.webhooks.patch_webhook("webhook_id", request)
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