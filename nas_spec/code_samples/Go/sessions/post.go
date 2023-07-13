// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/common"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/sessions"
	"github.com/checkout/checkout-sdk-go/sessions/completion"
	"github.com/checkout/checkout-sdk-go/sessions/sources"
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
			WithScopes([]string{configuration.SessionsApp, configuration.SessionsBrowser}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

source := sources.NewSessionCardSource()
source.Number = "12345678"
source.ExpiryMonth = 10
source.ExpiryYear = 2027

completion := completion.NewHostedCompletion()
completion.SuccessUrl = "https://docs.checkout.com/sessions/success"
completion.FailureUrl = "https://docs.checkout.com/sessions/fail"

request := sessions.SessionRequest{
	Source:                 source,
	Amount:                 10,
	Currency:               common.GBP,
	ProcessingChannelId:    "processing_channel_id",
	AuthenticationType:     sessions.RegularAuthType,
	AuthenticationCategory: sessions.Payment,
	ChallengeIndicator:     common.NoPreference,
	Reference:              "reference",
	TransactionType:        sessions.GoodsService,
	ShippingAddress: &sources.SessionAddress{
		Address: common.Address{
			AddressLine1: "Checkout.com",
			AddressLine2: "90 Tottenham Court Road",
			City:         "London",
			State:        "London",
			Zip:          "W1T 4TJ",
			Country:      common.GB,
		},
	},
	Completion:  completion,
}

response, err := api.Sessions.RequestSession(request)
if err != nil {
	return nil, err
}

return response, nil
