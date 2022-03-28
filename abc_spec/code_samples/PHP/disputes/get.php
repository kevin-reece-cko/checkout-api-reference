// For more information please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutDefaultSdk;
use Checkout\\Disputes\\DisputesQueryFilter;
use Checkout\\Environment;


$builder = CheckoutDefaultSdk::staticKeys();
$builder->setPublicKey("public_key");
$builder->setSecretKey("secret_key");
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();

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
$from->sub(new DateInterval("P1Y"));
$query->from = $from;

try {
    $response = $api->getDisputesClient()->query($query);
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
