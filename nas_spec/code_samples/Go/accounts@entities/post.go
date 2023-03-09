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

request := accounts.OnboardEntityRequest{
	Reference: "reference",
	ContactDetails: &accounts.ContactDetails{
		Phone: &accounts.Phone{Number: "4155552671"},
	},
	Profile: &accounts.Profile{
		Urls: []string{"https://docs.checkout.com/1", "https://docs.checkout.com/2"},
		Mccs: []string{"0742"},
	},
	Individual: &accounts.Individual{
		FirstName:     "FirstName",
		MiddleName:    "",
		LastName:      "LastName",
		TradingName:   "TradingName",
		NationalTaxId: "TAX123456",
		RegisteredAddress: &common.Address{
			AddressLine1: "Checkout.com",
			AddressLine2: "90 Tottenham Court Road",
			City:         "London",
			State:        "London",
			Zip:          "W1T 4TJ",
			Country:      common.GB,
		},
		DateOfBirth: &accounts.DateOfBirth{
			Day:   5,
			Month: 6,
			Year:  1990,
		},
		Identification: &accounts.Identification{
			NationalIdNumber: "AB123456C",
			Document: &accounts.Document{
				Front: "number",
				Back:  "number",
			},
		},
	},
}

response, err := api.Accounts.CreateEntity(request)
if err != nil {
	return nil, err
}

return response, nil
