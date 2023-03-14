// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/workflows/reflows"
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
			WithScopes([]string{configuration.Flow}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

request := &reflows.ReflowByEventsRequest{
	Events:          []string{"event_id_1", "event_id_2"},
	ReflowWorkflows: reflows.ReflowWorkflows{Workflows: []string{"workflow_id_1", "workflow_id_2"}},
}

response, err := api.WorkFlows.Reflow(request)
if err != nil {
	return nil, err
}

return response, nil
