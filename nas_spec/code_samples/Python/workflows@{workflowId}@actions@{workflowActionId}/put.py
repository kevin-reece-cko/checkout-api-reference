# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.oauth_scopes import OAuthScopes
from checkout_sdk.workflows.workflows import WebhookSignature, WebhookWorkflowActionRequest

# API Keys
api = CheckoutSdk.builder() \\
    .secret_key('secret_key') \\
    .environment(Environment.sandbox()) \\
    .build()
    # or Environment.production()

# OAuth
api = CheckoutSdk.builder() \\
    .oauth() \\
    .client_credentials('client_id', 'client_secret') \\
    .environment(Environment.sandbox()) \\
    .scopes([OAuthScopes.FLOW, OAuthScopes.FLOW_WORKFLOWS]) \\
    .build()

signature = WebhookSignature()
signature.key = '8V8x0dLK%AyD*DNS8JJr'
signature.method = 'HMACSHA256'

request = WebhookWorkflowActionRequest()
request.url = 'https://docs.checkout.com/fail/fake'
request.signature = signature

try:
    response = api.workflows.update_workflow_action('workflow_id', 'action_id', request)
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