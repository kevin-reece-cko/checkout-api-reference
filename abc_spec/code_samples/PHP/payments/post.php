// For more information please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutDefaultSdk;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\Currency;
use Checkout\\Common\\Phone;
use Checkout\\Environment;
use Checkout\\Payments\\PaymentRequest;
use Checkout\\Payments\\Source\\RequestCardSource;

$builder = CheckoutDefaultSdk::staticKeys();
$builder->setPublicKey("public_key");
$builder->setSecretKey("secret_key");
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();

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
    $response = $api->getPaymentsClient()->requestPayment($request); // or "requestPayout"
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
