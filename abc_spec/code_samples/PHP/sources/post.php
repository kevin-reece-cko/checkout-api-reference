<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\Phone;
use Checkout\\Environment;
use Checkout\\Sources\\Previous\\SepaSourceRequest;
use Checkout\\Sources\\Previous\\SourceData;

$api = CheckoutSdk::builder()
    ->previous()
    ->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

$phone = new Phone();
$phone->country_code = "44";
$phone->number = "020 222333";

$address = new Address();
$address->address_line1 = "CheckoutSdk.com";
$address->address_line2 = "90 Tottenham Court Road";
$address->city = "London";
$address->state = "London";
$address->zip = "W1T 4TJ";
$address->country = Country::$GB;

$sourceData = new SourceData();
$sourceData->first_name = "FirstName";
$sourceData->last_name = "LastName";
$sourceData->account_iban = "IBAN";
$sourceData->bic = "BIC";
$sourceData->billing_descriptor = "descriptor";
$sourceData->mandate_type = "single";

$request = new SepaSourceRequest();
$request->billing_address = $address;
$request->phone = $phone;
$request->reference = "reference";
$request->source_data = $sourceData;

try {
    $response = $api->getSourcesClient()->createSepaSource($request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}