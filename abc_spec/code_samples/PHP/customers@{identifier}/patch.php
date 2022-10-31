<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Common\\Phone;
use Checkout\\Customers\\CustomerRequest;
use Checkout\\Environment;

$api = CheckoutSdk::builder()
    ->previous()
    ->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

$phone = new Phone();
$phone->country_code = "1";
$phone->number = "4155552671";

$customerRequest = new CustomerRequest();
$customerRequest->email = "email@docs.checkout.com";
$customerRequest->name = "name";
$customerRequest->phone = $phone;

try {
    $response = $api->getCustomersClient()->update("customer_id", $customerRequest);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}