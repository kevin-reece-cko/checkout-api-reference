// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/common"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/sources"
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

request := sources.NewSepaSourceRequest()
request.BillingAddress = &common.Address{
	AddressLine1: "Checkout.com",
	AddressLine2: "90 Tottenham Court Road",
	City:         "London",
	State:        "London",
	Zip:          "W1T 4TJ",
	Country:      common.GB,
}
request.SourceData = &sources.SourceData{
	FirstName:         "first name",
	LastName:          "last name",
	AccountIban:       "account iban",
	Bic:               "bic",
	BillingDescriptor: "billing descriptor",
	MandateType:       "mandate type",
}
request.Reference = "reference"
request.Phone = &common.Phone{
	CountryCode: "1",
	Number:      "415 555 2671",
}

response, err := api.Sources.CreateSepaSource(request)
if err != nil {
	return nil, err
}

return response, nil
