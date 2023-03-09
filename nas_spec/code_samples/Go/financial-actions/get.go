// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/financial"
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
			WithScopes([]string{configuration.FinancialActions}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

query := financial.QueryFilter{
	PaymentId: "payment_id",
	ActionId:  "action_id",
	Limit:     20,
}

response, err := api.Financial.GetFinancialActions(query)
if err != nil {
	return nil, err
}

return response, nil
