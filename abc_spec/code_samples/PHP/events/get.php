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

$request = new DisputesQueryFilter();
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
    $request_id = $e->request_id;
    $http_status_code = $e->http_status_code;
    $error_details = $e->error_details;
} catch (CheckoutArgumentException $e) {
    // Bad arguments
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
