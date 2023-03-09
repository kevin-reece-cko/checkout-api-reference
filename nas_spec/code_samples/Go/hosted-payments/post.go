// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/common"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/payments"
	"github.com/checkout/checkout-sdk-go/payments/hosted"
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

address := common.Address{
	AddressLine1: "Checkout.com",
	AddressLine2: "90 Tottenham Court Road",
	City:         "London",
	State:        "London",
	Zip:          "W1T 4TJ",
	Country:      common.GB,
}

phone := common.Phone{
	CountryCode: "1",
	Number:      "415 555 2671",
}

request := hosted.HostedPaymentRequest{
	Amount:      10,
	Currency:    common.GBP,
	PaymentType: payments.Regular,
	BillingDescriptor: &payments.BillingDescriptor{
		Name: "Name",
		City: "London",
	},
	Reference:   "Reference",
	Description: "Description",
	Customer: &common.CustomerRequest{
		Email: "email@docs.checkout.com",
		Name:  "Name",
	},
	Shipping: &payments.ShippingDetails{
		Address: &address,
		Phone:   &phone,
	},
	Billing: &payments.BillingInformation{
		Address: &address,
		Phone:   &phone,
	},
	Recipient: &payments.PaymentRecipient{
		DateOfBirth:   "1985-05-15",
		AccountNumber: "999999999",
		Zip:           "12345",
		LastName:      "LastName",
	},
	Processing: &payments.ProcessingSettings{Aft: true},
	Products: []payments.Product{
		{
			Name:     "name",
			Quantity: 1,
			Price:    200,
		},
	},
	Risk:       &payments.RiskRequest{Enabled: false},
	SuccessUrl: "https://docs.checkout.com/payments/success",
	CancelUrl:  "https://docs.checkout.com/payments/cancel",
	FailureUrl: "https://docs.checkout.com/payments/failure",
	Locale:     "en-GB",
	ThreeDs: &payments.ThreeDsRequest{
		Enabled:            false,
		AttemptN3D:         false,
		ChallengeIndicator: common.NoChallengeRequested,
	},
	Capture:   true,
	CaptureOn: time.Now().AddDate(0, 0, 30),
}

response, err := api.Hosted.CreateHostedPaymentsPageSession(request)
if err != nil {
	return nil, err
}

return response, nil
