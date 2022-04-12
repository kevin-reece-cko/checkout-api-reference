# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.four.oauth_scopes import OAuthScopes
from checkout_sdk.workflows.workflows import EventWorkflowConditionRequest

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
    .scopes([OAuthScopes.FLOW, OAuthScopes.FLOW_WORKFLOWS, OAuthScopes.FLOW_EVENTS]) \\
    .build()

request = EventWorkflowConditionRequest()
request.events = {'gateway': ['card_verified',
                              'card_verification_declined',
                              'payment_approved',
                              'payment_pending',
                              'payment_declined',
                              'payment_voided',
                              'payment_captured',
                              'payment_refunded'],
                  'dispute': ['dispute_canceled',
                              'dispute_evidence_required',
                              'dispute_expired',
                              'dispute_lost',
                              'dispute_resolved',
                              'dispute_won']}

try:
    response = api.workflows.update_workflow_condition('workflow_id', 'condition_id', request)
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
