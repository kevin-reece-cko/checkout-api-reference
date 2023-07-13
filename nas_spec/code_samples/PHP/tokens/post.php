<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\Phone;
use Checkout\\Environment;
use Checkout\\Tokens\\CardTokenRequest;

$api = CheckoutSdk::builder()
    ->staticKeys()
    ->environment(Environment::sandbox())
    ->publicKey("public_ey")
    ->build();

$phone = new Phone();
$phone->country_code = "+1";
$phone->number = "415 555 2671";

$address = new Address();
$address->address_line1 = "CheckoutSdk.com";
$address->address_line2 = "90 Tottenham Court Road";
$address->city = "London";
$address->state = "London";
$address->zip = "W1T 4TJ";
$address->country = Country::$GB;

$request = new CardTokenRequest();
$request->name = "Name";
$request->number = "123456789";
$request->expiry_year = 2027;
$request->expiry_month = 10;
$request->cvv = "123";
$request->billing_address = $address;
$request->phone = $phone;

try {
    $response = $api->getTokensClient()->requestCardToken($request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
