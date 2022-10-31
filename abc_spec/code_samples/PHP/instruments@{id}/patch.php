<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\Phone;
use Checkout\\Environment;
use Checkout\\Instruments\\Previous\\InstrumentAccountHolder;
use Checkout\\Instruments\\Previous\\UpdateInstrumentRequest;

$api = CheckoutSdk::builder()
    ->previous()
    ->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

$address = new Address();
$address->address_line1 = "CheckoutSdk.com";
$address->address_line2 = "90 Tottenham Court Road";
$address->city = "London";
$address->state = "London";
$address->zip = "W1T 4TJ";
$address->country = Country::$GB;

$phone = new Phone();
$phone->country_code = "+1";
$phone->number = "415 555 2671";

$instrumentAccountHolder = new InstrumentAccountHolder();
$instrumentAccountHolder->billing_address = $address;
$instrumentAccountHolder->phone = $phone;

$request = new UpdateInstrumentRequest();
$request->expiry_month = 10;
$request->expiry_year = 2027;
$request->name = "FirstName LastName";
$request->account_holder = $instrumentAccountHolder;

try {
    $response = $api->getInstrumentsClient()->update("instrument_id", $request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
