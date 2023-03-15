// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/metadata"
	"github.com/checkout/checkout-sdk-go/metadata/sessions"
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
			WithScopes([]string{configuration.VaultCardMetadata}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

request := metadata.CardMetadataRequest{
	Source: sources.NewRequestCardSource("card_number"),
	Format: metadata.Basic,
}

response, err := api.Metadata.RequestCardMetadata(request)
if err != nil {
	return nil, err
}

return response, nil
