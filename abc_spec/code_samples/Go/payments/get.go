// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/payments"
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

query := payments.QueryRequest{
	Limit:     10,
	Skip:      0,
	Reference: "reference",
}

response, err := api.Payments.RequestPaymentList(query)
if err != nil {
	return nil, err
}

return response, nil

