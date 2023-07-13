# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.disputes.disputes import DisputeEvidenceRequest
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.oauth_scopes import OAuthScopes

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
    .scopes([OAuthScopes.DISPUTES, OAuthScopes.DISPUTES_PROVIDE_EVIDENCE]) \\
    .build()

evidence_request = DisputeEvidenceRequest()
evidence_request.proof_of_delivery_or_service_file = 'proof_of_delivery_or_service_file'
evidence_request.proof_of_delivery_or_service_text = 'proof of delivery or service text'
evidence_request.invoice_or_receipt_file = 'invoice_or_receipt_file'
evidence_request.invoice_or_receipt_text = 'invoice_or_receipt_text'
evidence_request.customer_communication_file = 'customer_communication_file'
evidence_request.customer_communication_text = 'customer_communication_text'
evidence_request.additional_evidence_file = 'additional_evidence_file'
evidence_request.additional_evidence_text = 'additional_evidence_text'

try:
    response = api.disputes.put_evidence('dispute_id', evidence_request)
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
