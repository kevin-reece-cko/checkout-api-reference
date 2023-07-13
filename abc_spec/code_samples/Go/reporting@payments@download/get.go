// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/common"
	"github.com/checkout/checkout-sdk-go/configuration"
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

query := common.DateRangeQuery{
	From: time.Parse("2006-01-02T15:04:05Z", time.Now().AddDate(0, -1, 0).Format("2006-01-02T15:04:05Z")),
	To:   time.Parse("2006-01-02T15:04:05Z", time.Now().Format("2006-01-02T15:04:05Z")),
}

response, err := api.Reconciliation.RetrieveCVSPaymentsReport(query)
if err != nil {
	return nil, err
}

return response, nil
