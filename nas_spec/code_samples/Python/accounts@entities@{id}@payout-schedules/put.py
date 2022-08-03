# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.accounts.accounts import UpdateScheduleRequest, ScheduleRequest, ScheduleFrequencyWeeklyRequest, DaySchedule
from checkout_sdk.common.enums import Currency
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

frequency = ScheduleFrequencyWeeklyRequest()
frequency.by_day = DaySchedule.MONDAY

schedule = ScheduleRequest()
schedule.frequency = frequency

request = UpdateScheduleRequest()
request.enabled = True
request.threshold = 1000
request.recurrence = schedule

try:
    response = api.accounts.update_payout_schedule("entity_id", Currency.USD, request)
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