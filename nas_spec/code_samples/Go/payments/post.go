// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/common"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/payments"
	"github.com/checkout/checkout-sdk-go/payments/nas"
	"github.com/checkout/checkout-sdk-go/payments/nas/sessions"
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
			WithScopes([]string{configuration.Gateway, configuration.GatewayPayment}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

source := sources.NewRequestCardSource()
source.Number = "123456789"
source.ExpiryMonth = 10
source.ExpiryYear = 2027
source.Cvv = "123"
source.Stored = false

sender := nas.NewRequestIndividualSender()
sender.FirstName = "FirstName"
sender.LastName = "LastName"
sender.Address = &common.Address{
	AddressLine1: "Checkout.com",
	AddressLine2: "90 Tottenham Court Road",
	City:         "London",
	State:        "London",
	Zip:          "W1T 4TJ",
	Country:      common.GB,
}
sender.Identification = &nas.Identification{
	Type:           nas.DrivingLicence,
	Number:         "1234",
	IssuingCountry: common.GB,
}

request := nas.PaymentRequest{
	Source:              source,
	Amount:              10,
	Currency:            common.GBP,
	Reference:           "reference",
	Capture:             false,
	ThreeDsRequest:      &payments.ThreeDsRequest{
		Enabled:               true,
		ChallengeIndicator:    common.NoChallengeRequested,
	},
	ProcessingChannelId: "processing_channel_id",
	SuccessUrl:          "https://docs.checkout.com/success",
	FailureUrl:          "https://docs.checkout.com/failure",
	Sender:              sender,
}

response, err := api.Payments.RequestPayment(request, nil) // or "RequestPayout(request PayoutRequest, idempotencyKey *string)"
if err != nil {
	return nil, err
}

return response, nil
