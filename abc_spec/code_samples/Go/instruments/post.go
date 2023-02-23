// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/common"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/instruments"
	"github.com/checkout/checkout-sdk-go/instruments/abc"
)

api, err := checkout.
			Builder().
			Previous().
			WithSecretKey("secret_key").
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

request := abc.CreateInstrumentRequest{
	Type:  instruments.Token,
	Token: "token",
	AccountHolder: &abc.InstrumentAccountHolder{
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
	},
}

response, err := api.Instruments.Create(request)
if err != nil {
	return nil, err
}

return response, nil
