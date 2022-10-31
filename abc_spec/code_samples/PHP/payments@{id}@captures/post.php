<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Environment;
use Checkout\\Payments\\Previous\\CaptureRequest;

$api = CheckoutSdk::builder()
    ->previous()
    ->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

$request = new CaptureRequest();
$request->reference = "reference";
$request->amount = 5;

try {
    // or, capturePayment("payment_id") for a full capture
    $response = $api->getPaymentsClient()->capturePayment("payment_id", $request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
