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
    $request_id = $e->request_id;
    $http_status_code = $e->http_status_code;
    $error_details = $e->error_details;
} catch (CheckoutArgumentException $e) {
    // Bad arguments
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}