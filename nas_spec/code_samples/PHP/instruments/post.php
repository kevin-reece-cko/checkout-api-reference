<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Common\\AccountHolder;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\Phone;
use Checkout\\Environment;
use Checkout\\Instruments\\Create\\CreateCustomerInstrumentRequest;
use Checkout\\Instruments\\Create\\CreateTokenInstrumentRequest;
use Checkout\\OAuthScope;

//API Keys
$api = CheckoutSdk::builder()->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

//OAuth
$api = CheckoutSdk::builder()->oAuth()
    ->clientCredentials("client_id", "client_secret")
    ->scopes([OAuthScope::$Vault])
    ->environment(Environment::sandbox())
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

$accountHolder = new AccountHolder();
$accountHolder->first_name = "John";
$accountHolder->last_name = "Smith";
$accountHolder->phone = $phone;
$accountHolder->billing_address = $address;

$createCustomerInstrumentRequest = new CreateCustomerInstrumentRequest();
$createCustomerInstrumentRequest->id = "customer_id";

$request = new CreateTokenInstrumentRequest();
$request->token = "token";
$request->account_holder = $accountHolder;
$request->customer = $createCustomerInstrumentRequest;

try {
    $response = $api->getInstrumentsClient()->create($request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}