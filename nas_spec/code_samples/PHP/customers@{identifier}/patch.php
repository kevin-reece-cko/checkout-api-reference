<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Common\\Phone;
use Checkout\\Customers\\CustomerRequest;
use Checkout\\Environment;
use Checkout\\OAuthScope;

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

$phone = new Phone();
$phone->country_code = "+1";
$phone->number = "415 555 2671";

$request = new CustomerRequest();
$request->email = "email@docs.checkout.com";
$request->name = "name";
$request->phone = $phone;
$request->instruments = ["instrument_id_1", "instrument_id_2"];

try {
    $response = $api->getCustomersClient()->update("customer_id", $request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}