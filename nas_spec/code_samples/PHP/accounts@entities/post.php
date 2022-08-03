<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\Accounts\\ContactDetails;
use Checkout\\Accounts\\DateOfBirth;
use Checkout\\Accounts\\Identification;
use Checkout\\Accounts\\Individual;
use Checkout\\Accounts\\OnboardEntityRequest;
use Checkout\\Accounts\\Profile;
use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\Phone;
use Checkout\\Environment;
use Checkout\\OAuthScope;

$api = CheckoutSdk::builder()->oAuth()
    ->clientCredentials("client_id", "client_secret")
    ->scopes([OAuthScope::$Accounts])
    ->environment(Environment::sandbox())
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

$dateOfBirth = new DateOfBirth();
$dateOfBirth->day = 5;
$dateOfBirth->month = 6;
$dateOfBirth->year = 1996;

$identification = new Identification();
$identification->national_id_number = "AB123456C";

$request = new OnboardEntityRequest();
$request->reference = uniqid();
$request->contact_details = new ContactDetails();
$request->contact_details->phone = $phone;
$request->profile = new Profile();
$request->profile->urls = array("https://www.superheroexample.com");
$request->profile->mccs = array("0742");
$request->individual = new Individual();
$request->individual->first_name = "Bruce";
$request->individual->last_name = "Wayne";
$request->individual->trading_name = "Batman's Super Hero Masks";
$request->individual->registered_address = $address;
$request->individual->national_tax_id = "TAX123456";
$request->individual->date_of_birth = $dateOfBirth;
$request->individual->identification = $identification;

try {
    $response = $api->getAccountsClient()->createEntity($request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}