# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.oauth_scopes import OAuthScopes
from checkout_sdk.workflows.workflows import WebhookSignature, WebhookWorkflowActionRequest, EntityWorkflowConditionRequest, \\
                                        EventWorkflowConditionRequest, ProcessingChannelWorkflowConditionRequest, CreateWorkflowRequest

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
signature.key = 'signature'
signature.method = 'HMACSHA256'

action_request = WebhookWorkflowActionRequest()
action_request.url = 'https://docs.checkout.com/fail'
action_request.headers = {}
action_request.signature = signature

entity_condition_request = EntityWorkflowConditionRequest()
entity_condition_request.entities = ['entity_id']

event_condition_request = EventWorkflowConditionRequest()
event_condition_request.events = {'gateway': ['payment_approved',
                                              'payment_declined',
                                              'card_verification_declined',
                                              'card_verified',
                                              'payment_authorization_incremented',
                                              'payment_authorization_increment_declined',
                                              'payment_capture_declined',
                                              'payment_captured',
                                              'payment_refund_declined',
                                              'payment_refunded',
                                              'payment_void_declined',
                                              'payment_voided'],
                                  'dispute': ['dispute_canceled',
                                              'dispute_evidence_required',
                                              'dispute_expired',
                                              'dispute_lost',
                                              'dispute_resolved',
                                              'dispute_won']}

processing_channel_condition_request = ProcessingChannelWorkflowConditionRequest()
processing_channel_condition_request.processing_channels = ['processing_channel_id']

request = CreateWorkflowRequest()
request.actions = [action_request]
request.conditions = [entity_condition_request, event_condition_request,
                      processing_channel_condition_request]
request.name = 'Name'
request.active = True

try:
    response = api.workflows.create_workflow(request)
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