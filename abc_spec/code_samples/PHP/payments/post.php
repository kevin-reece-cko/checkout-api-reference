<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\Currency;
use Checkout\\Common\\Phone;
use Checkout\\Environment;
use Checkout\\Payments\\Previous\\PaymentRequest;
use Checkout\\Payments\\Previous\\Source\\RequestCardSource;

$api = CheckoutSdk::builder()
    ->previous()
    ->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

$billingAddress = new Address();
$billingAddress->address_line1 = "CheckoutSdk.com";
$billingAddress->address_line2 = "90 Tottenham Court Road";
$billingAddress->city = "London";
$billingAddress->state = "London";
$billingAddress->zip = "W1T 4TJ";
$billingAddress->country = Country::$GB;

$phone = new Phone();
$phone->country_code = "+1";
$phone->number = "415 555 2671";

$requestCardSource = new RequestCardSource();
$requestCardSource->name = "Name";
$requestCardSource->number = "Number";
$requestCardSource->expiry_year = 2026;
$requestCardSource->expiry_month = 10;
$requestCardSource->cvv = "123";
$requestCardSource->billing_address = $billingAddress;
$requestCardSource->phone = $phone;

$request = new PaymentRequest();
$request->source = $requestCardSource;
$request->capture = true;
$request->reference = "reference";
$request->amount = 10;
$request->currency = Currency::$GBP;

try {
    $response = $api->getPaymentsClient()->requestPayment($request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
