// For more information please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutDefaultSdk;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\Phone;
use Checkout\\Environment;
use Checkout\\Sources\\SepaSourceRequest;
use Checkout\\Sources\\SourceData;

$builder = CheckoutDefaultSdk::staticKeys();
$builder->setPublicKey("public_key");
$builder->setSecretKey("secret_key");
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();

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
    $request_id = $e->request_id;
    $http_status_code = $e->http_status_code;
    $error_details = $e->error_details;
} catch (CheckoutArgumentException $e) {
    // Bad arguments
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
