// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/common"
	"github.com/checkout/checkout-sdk-go/configuration"
	instruments "github.com/checkout/checkout-sdk-go/instruments/nas"
)

// OAuth
api, err := checkout.
			Builder().
			OAuth().
			WithClientCredentials("client_id", "client_secret").
			WithScopes([]string{configuration.PayoutsBankDetails}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

query := instruments.QueryBankAccountFormatting{
	AccountHolderType: common.Individual,
	PaymentNetwork:    instruments.Local,
}

response, err := api.Instruments.GetBankAccountFieldFormatting("GB", "GBP", query)
if err != nil {
	return nil, err
}

return response, nil
