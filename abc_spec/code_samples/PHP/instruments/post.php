// For more information please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutDefaultSdk;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\InstrumentType;
use Checkout\\Common\\Phone;
use Checkout\\Environment;
use Checkout\\Instruments\\CreateInstrumentRequest;
use Checkout\\Instruments\\InstrumentAccountHolder;
use Checkout\\Instruments\\InstrumentCustomerRequest;

$builder = CheckoutDefaultSdk::staticKeys();
$builder->setPublicKey("public_key");
$builder->setSecretKey("secret_key");
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();

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
    $request_id = $e->request_id;
    $http_status_code = $e->http_status_code;
    $error_details = $e->error_details;
} catch (CheckoutArgumentException $e) {
    // Bad arguments
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
