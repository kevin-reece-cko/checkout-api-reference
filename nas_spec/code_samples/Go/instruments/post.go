// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/common"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/instruments/nas"
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
			WithScopes([]string{configuration.Gateway}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

request := nas.NewCreateTokenInstrumentRequest()
request.Token = "token"
request.AccountHolder = &common.AccountHolder{
	FirstName: "FirstName",
	LastName:  "LastName",
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
request.Customer = &nas.CreateCustomerInstrumentRequest{Id: "customer_id"}

response, err := api.Instruments.Create(request)
if err != nil {
	return nil, err
}

return response, nil
