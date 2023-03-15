// For more information please refer to https://github.com/checkout/checkout-sdk-go
import (
	"github.com/checkout/checkout-sdk-go"
	"github.com/checkout/checkout-sdk-go/configuration"
	"github.com/checkout/checkout-sdk-go/disputes"
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
			WithScopes([]string{configuration.Disputes}).
			WithEnvironment(configuration.Sandbox()). // or Environment.PRODUCTION
			Build()
if err != nil {
	return nil, err
}

request := disputes.Evidence{
	ProofOfDeliveryOrServiceFile:           "file_id",
	ProofOfDeliveryOrServiceText:           "proof of delivery or service text",
	InvoiceOrReceiptFile:                   "file_id",
	InvoiceOrReceiptText:                   "proof of receipt text",
	InvoiceShowingDistinctTransactionsFile: "file_id",
	InvoiceShowingDistinctTransactionsText: "invoice showing distinct transactions text",
	CustomerCommunicationFile:              "file_id",
	CustomerCommunicationText:              "customer communication text",
	RefundOrCancellationPolicyFile:         "file_id",
	RefundOrCancellationPolicyText:         "refund or cancellation policy text",
	RecurringTransactionAgreementFile:      "file_id",
	RecurringTransactionAgreementText:      "recurring transaction agreement text",
	AdditionalEvidenceFile:                 "file_id",
	AdditionalEvidenceText:                 "additional evidence text",
	ProofOfDeliveryOrServiceDateFile:       "file_id",
	ProofOfDeliveryOrServiceDateText:       "proof of delivery or service date text",
}

response, err := api.Disputes.PutEvidence("dispute_id", request)
if err != nil {
	return nil, err
}

return response, nil
