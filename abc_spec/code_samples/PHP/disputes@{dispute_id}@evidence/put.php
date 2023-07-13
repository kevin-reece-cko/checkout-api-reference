<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Disputes\\DisputeEvidenceRequest;
use Checkout\\Environment;

$api = CheckoutSdk::builder()
    ->previous()
    ->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

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
    $response = $api->getDisputesClient()->putEvidence("dispute_id", $request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
