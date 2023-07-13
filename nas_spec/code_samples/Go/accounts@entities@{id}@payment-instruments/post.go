// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/accounts"
	"github.com/checkout/checkout-sdk-go/common"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/instruments"
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
			WithScopes([]string{configuration.Accounts}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

request := accounts.PaymentInstrumentRequest{
	Label:              "Bob's Bank Account",
	Type:               instruments.BankAccount,
	Currency:           common.GBP,
	Country:            common.GB,
	DefaultDestination: true,
	Document: &accounts.InstrumentDocument{
		Type:   "document",
		FileId: "file",
	},
	InstrumentDetails: &accounts.InstrumentDetailsFasterPayments{
		AccountNumber: "123456789",
		BankCode:      "bank_code",
	},
}

response, err := api.Accounts.CreatePaymentInstrument("entity_id", request)
if err != nil {
	return nil, err
}

return response, nil
