// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/reports"
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
			WithScopes([]string{configuration.Reports}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

query := reports.QueryFilter{
	CreatedAfter:  time.Parse("2006-01-02", time.Now().AddDate(0, 0, -10).Format("2006-01-02")),
	CreatedBefore: time.Parse("2006-01-02", time.Now().Format("2006-01-02")),
	EntityId:      "entity_id",
	Limit:         20,
}

response, err := api.Reports.GetAllReports(query)
if err != nil {
	return nil, err
}

return response, nil
