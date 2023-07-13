// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/transfers"
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
			WithScopes([]string{configuration.Transfers}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

request := transfers.TransferRequest{
	Reference:    "reference",
	TransferType: transfers.Commission,
	Source: &transfers.TransferSourceRequest{
		Id:     "entity_id",
		Amount: 100,
	},
	Destination: &transfers.TransferDestinationRequest{Id: "destination_id"},
}

response, err := api.Transfers.InitiateTransferOfFounds(request, nil)
if err != nil {
	return nil, err
}

return response, nil
