<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Disputes\\DisputesQueryFilter;
use Checkout\\Environment;


$api = CheckoutSdk::builder()
    ->previous()
    ->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

$query = new DisputesQueryFilter();
$query->payment_id = "payment_id";
$query->payment_arn = "payment_arn";
$query->payment_reference = "payment_reference";
$query->statuses = "comma,separated,list,statuses";
$query->limit = 10;
$query->skip = 5;
$query->to = new DateTime(); // UTC, now

$from = new DateTime();
$from->setTimezone(new DateTimeZone("America/Mexico_City"));
$from->sub(new DateInterval("P1M"));
$query->from = $from;

try {
    $response = $api->getDisputesClient()->query($query);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
