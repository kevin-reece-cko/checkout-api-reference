// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"fmt"
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/balances"
	"github.com/checkout/checkout-sdk-go/common"
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
			WithScopes([]string{configuration.Balances}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

query := balances.QueryFilter{Query: fmt.Sprintf("currency:%s", common.GBP)}

response, err := api.Balances.RetrieveEntityBalances("entity_id", query)
if err != nil {
	return nil, err
}

return response, nil
