// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/configuration"
	events "github.com/checkout/checkout-sdk-go/events/abc"
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

query := events.QueryRetrieveEvents{
	PaymentId: "payment_id",
	Skip:      0,
	Limit:     10,
	From:      time.Now().AddDate(0, -2, 0).String(),
	To:        time.Now().String(),
}

response, err := api.Events.RetrieveEventsQuery(query)
if err != nil {
	return nil, err
}

return response, nil
