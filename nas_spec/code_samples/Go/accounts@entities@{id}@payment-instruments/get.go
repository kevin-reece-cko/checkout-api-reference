// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/accounts"
	"github.com/checkout/checkout-sdk-go/configuration"
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
	WithScopes([]string{configuration.Accounts}).
	WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
	Build()
if err != nil {
	return nil, err
}

query := accounts.PaymentInstrumentsQuery{Status: "pending"}

response, err := api.Accounts.QueryPaymentInstruments("entity_id", query)
if err != nil {
	return nil, err
}

return response, nil
