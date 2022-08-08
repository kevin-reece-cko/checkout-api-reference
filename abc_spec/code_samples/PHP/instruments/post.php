<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\InstrumentType;
use Checkout\\Common\\Phone;
use Checkout\\Environment;
use Checkout\\Instruments\\Previous\\CreateInstrumentRequest;
use Checkout\\Instruments\\Previous\\InstrumentAccountHolder;
use Checkout\\Instruments\\Previous\\InstrumentCustomerRequest;

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

$customer = new InstrumentCustomerRequest();
$customer->email = "email@docs.checkout.com";
$customer->name = "Name";
$customer->phone = $phone;
$customer->default = true;

$request = new CreateInstrumentRequest();
$request->type = InstrumentType::$token;
$request->token = "token";
$request->account_holder = $instrumentAccountHolder;
$request->customer = $customer;

try {
    $response = $api->getInstrumentsClient()->create($request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
