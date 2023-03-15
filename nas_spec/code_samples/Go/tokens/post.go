// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/common"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/tokens"
)

// API Keys
api, err := checkout.
			Builder().
			Previous().
			WithPublicKey("public_key").
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

request := tokens.CardTokenRequest{
	Type:        tokens.Card,
	Number:      "123456789",
	ExpiryMonth: 10,
	ExpiryYear:  2025,
	Name:        "Name",
	CVV:         "123",
	BillingAddress: &common.Address{
		AddressLine1: "Checkout.com",
		AddressLine2: "90 Tottenham Court Road",
		City:         "London",
		State:        "London",
		Zip:          "W1T 4TJ",
		Country:      common.GB,
	},
	Phone: &common.Phone{
		CountryCode: "1",
		Number:      "415 555 2671",
	},
}

response, err := api.Tokens.RequestCardToken(request)
if err != nil {
	return nil, err
}

return response, nil
