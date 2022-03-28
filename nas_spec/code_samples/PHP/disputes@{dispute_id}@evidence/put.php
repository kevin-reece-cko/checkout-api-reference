// Please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutFourSdk;
use Checkout\\Disputes\\DisputeEvidenceRequest;
use Checkout\\Environment;
use Checkout\\Four\\FourOAuthScope;

// API Keys
$builder = CheckoutFourSdk::staticKeys();
$builder->setPublicKey("public_key");
$builder->setSecretKey("secret_key");
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();

// OAuth
$builder = CheckoutFourSdk::oAuth();
$builder->clientCredentials("client_id", "client_secret");
$builder->scopes([FourOAuthScope::$Disputes]); // more scopes available
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$builder->setFilesEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();

$request = new DisputeEvidenceRequest();
$request->proof_of_delivery_or_service_file = "file_id";
$request->proof_of_delivery_or_service_text = "proof of delivery or service text";
$request->invoice_or_receipt_file = "file_id";
$request->invoice_or_receipt_text = "Copy of the invoice";
$request->customer_communication_file = "file_id";
$request->customer_communication_text = "Copy of an email exchange with the cardholder";
$request->additional_evidence_file = "file_id";
$request->additional_evidence_text = "Scanned document";

try {
    $api->getDisputesClient()->putEvidence("dispute_id", $request);
} catch (CheckoutApiException $e) {
    // API error
    $request_id = $e->request_id;
    $http_status_code = $e->http_status_code;
    $error_details = $e->error_details;
} catch (CheckoutArgumentException $e) {
    // Bad arguments
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
