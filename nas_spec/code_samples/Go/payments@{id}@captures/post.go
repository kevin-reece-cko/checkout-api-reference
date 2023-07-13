// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/payments/nas"
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
			WithScopes([]string{configuration.Gateway, configuration.GatewayPayment}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

request := nas.CaptureRequest{
	Amount:    10,
	Reference: "partial capture",
	Metadata: map[string]interface{}{},
}

response, err := api.Payments.CapturePayment("payment_id", request, nil)
if err != nil {
	return nil, err
}

return response, nil
