// Please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutFourSdk;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\Phone;
use Checkout\\Environment;
use Checkout\\Four\\FourOAuthScope;
use Checkout\\Marketplace\\ContactDetails;
use Checkout\\Marketplace\\DateOfBirth;
use Checkout\\Marketplace\\Identification;
use Checkout\\Marketplace\\Individual;
use Checkout\\Marketplace\\OnboardEntityRequest;
use Checkout\\Marketplace\\Profile;

// OAuth
$builder = CheckoutFourSdk::oAuth();
$builder->clientCredentials("client_id", "client_secret");
$builder->scopes([FourOAuthScope::$Marketplace]); // more scopes available
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$builder->setFilesEnvironment(Environment::sandbox()); // or Environment::production()
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

$dateOfBirth = new DateOfBirth();
$dateOfBirth->day = 5;
$dateOfBirth->month = 6;
$dateOfBirth->year = 1996;

$identification = new Identification();
$identification->national_id_number = "AB123456C";

$request = new OnboardEntityRequest();
$request->reference = "reference";
$request->contact_details = new ContactDetails();
$request->contact_details->phone = $phone;
$request->profile = new Profile();
$request->profile->urls = array("https://docs.checkout.com");
$request->profile->mccs = array("0742");
$request->individual = new Individual();
$request->individual->first_name = "FirstName";
$request->individual->last_name = "LastName";
$request->individual->trading_name = "Trading";
$request->individual->registered_address = $address;
$request->individual->national_tax_id = "TAX123456";
$request->individual->date_of_birth = $dateOfBirth;
$request->individual->identification = $identification;

try {
    $response = $api->getMarketplaceClient()->createEntity($request);
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
