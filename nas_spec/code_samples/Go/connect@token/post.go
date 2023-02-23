// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/configuration"
)

// SDK instantiation for OAuth
api, err := checkout.
			Builder().
			OAuth().
			WithClientCredentials("client_id", "client_secret").
			WithScopes([]string{configuration.Files, configuration.Flow, configuration.Fx, configuration.Gateway,
				configuration.Marketplace, configuration.SessionsApp, configuration.SessionsBrowser,
				configuration.Vault, configuration.PayoutsBankDetails, configuration.Disputes}). // more scopes available
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}
