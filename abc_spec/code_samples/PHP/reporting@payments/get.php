<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Environment;
use Checkout\\Reconciliation\\Previous\\ReconciliationQueryPaymentsFilter;

$api = CheckoutSdk::builder()
    ->previous()
    ->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

$from = new DateTime();
$from->setTimezone(new DateTimeZone("America/Mexico_City"));
$from->sub(new DateInterval("P1M"));

$filter = new ReconciliationQueryPaymentsFilter();
$filter->from = $from;
$filter->to = new DateTime();

try {
    $response = $api->getReconciliationClient()->queryPaymentsReport($filter);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
