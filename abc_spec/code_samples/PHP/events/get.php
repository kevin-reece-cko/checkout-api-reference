<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Environment;
use Checkout\\Events\\Previous\\RetrieveEventsRequest;

$api = CheckoutSdk::builder()
    ->previous()
    ->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

$request = new RetrieveEventsRequest();
$request->payment_id = "payment_id";
$request->charge_id = "payment_arn";
$request->reference = "reference";
$request->limit = 10;
$request->skip = 5;
$request->from = new DateTime();
$request->to = new DateTime();

try {
    $response = $api->getEventsClient()->retrieveEvents($request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
