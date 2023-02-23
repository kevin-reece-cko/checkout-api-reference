// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/common"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/payments/abc"
	"github.com/checkout/checkout-sdk-go/payments/abc/sources"
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

source := sources.NewRequestCardSource()
source.Name = "name"
source.Number = "number"
source.ExpiryMonth = 12
source.ExpiryYear = 2025
source.Cvv = "123"
source.Stored = false
source.BillingAddress = &common.Address{
	AddressLine1: "Checkout.com",
	AddressLine2: "90 Tottenham Court Road",
	City:         "London",
	State:        "London",
	Zip:          "W1T 4TJ",
	Country:      common.GB,
}
source.Phone = &common.Phone{
	CountryCode: "1",
	Number:      "415 555 2671",
}

request := abc.PaymentRequest{
	Source:    source,
	Amount:    10,
	Currency:  common.GBP,
	Reference: "Reference",
	Capture:   true,
}

response, err := api.Payments.RequestPayment(request, nil) // or "RequestPayout(request PayoutRequest, idempotencyKey *string)"
if err != nil {
	return nil, err
}

return response, nil
