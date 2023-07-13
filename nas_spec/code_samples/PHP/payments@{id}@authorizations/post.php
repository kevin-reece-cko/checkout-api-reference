<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Environment;
use Checkout\\OAuthScope;
use Checkout\\Payments\\AuthorizationRequest;

//API Keys
$api = CheckoutSdk::builder()->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

//OAuth
$api = CheckoutSdk::builder()->oAuth()
    ->clientCredentials("client_id", "client_secret")
    ->scopes([OAuthScope::$Gateway])
    ->environment(Environment::sandbox())
    ->build();

$request = new AuthorizationRequest();
$request->amount = 10;
$request->reference = "reference";
$request->metadata = array("param1" => "value1", "param2" => "value2");

try {
    // Optional: idempotencyKey as a third parameter for idempotent requests
    $response = $api->getPaymentsClient()->incrementPaymentAuthorization("payment_id", $request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}