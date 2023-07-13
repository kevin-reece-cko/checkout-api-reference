// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/payments/abc"
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

request := abc.CaptureRequest{
	Amount:    10,
	Reference: "partial capture",
	Metadata: map[string]interface{}{},
}

response, err := api.Payments.CapturePayment("payment_id", request, nil)
if err != nil {
	return nil, err
}

return response, nil
