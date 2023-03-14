// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/workflows"
	"github.com/checkout/checkout-sdk-go/workflows/actions"
	"github.com/checkout/checkout-sdk-go/workflows/conditions"
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

entityCondition := conditions.NewEntityConditionRequest()
entityCondition.Entities = []string{"workflow_entity_id"}

processingChannelCondition := conditions.NewProcessingChannelConditionRequest()
processingChannelCondition.ProcessingChannels = []string{"processing_channel_id"}

webhookAction := actions.NewWebhookActionRequest()
webhookAction.Url = "https://docs.checkout.com/webhook"
webhookAction.Headers = map[string]string{}
webhookAction.Signature = &actions.WebhookSignature{
	Key:    "8V8x0dLK%*DNS8JJr",
	Method: "HMACSHA256",
}

request := workflows.CreateWorkflowRequest{
	Name:       "name",
	Conditions: []conditions.ConditionsRequest{entityCondition, processingChannelCondition},
	Actions:    []actions.ActionsRequest{webhookAction},
}

response, err := api.WorkFlows.CreateWorkflow(request)
if err != nil {
	return nil, err
}

return response, nil
