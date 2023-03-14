// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/workflows/actions"
)

// API Keys
api, err := checkout.
			Builder().
			StaticKeys().
			WithSecretKey("secret_key").
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

// OAuth
api, err := checkout.
			Builder().
			OAuth().
			WithClientCredentials("client_id", "client_secret").
			WithScopes([]string{configuration.Flow, configuration.FlowWorkflows}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

request := actions.NewWebhookActionRequest()
request.Url = "https://docs.checkout.com/webhook"
request.Headers = map[string]string{}
request.Signature = &actions.WebhookSignature{
	Key:    "8V8x0dLK%*DNS8JJr",
	Method: "HMACSHA256",
}

response, err := api.WorkFlows.UpdateWorkflowAction("workflow_id", "action_id", request)
if err != nil {
	return nil, err
}

return response, nil
