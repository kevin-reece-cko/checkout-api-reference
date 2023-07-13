// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/accounts"
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
			WithScopes([]string{configuration.Accounts}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

request := accounts.CurrencySchedule{
	Enabled:    true,
	Threshold:  1000,
	Recurrence: accounts.NewScheduleFrequencyWeeklyRequest([]accounts.DaySchedule{accounts.Monday}),
}

response, err := api.Accounts.UpdatePayoutSchedule("entity_id", common.GBP, request)
if err != nil {
	return nil, err
}

return response, nil
