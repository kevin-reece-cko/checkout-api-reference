// Please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutFourSdk;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\Four\\AccountHolder;
use Checkout\\Common\\Phone;
use Checkout\\Environment;
use Checkout\\Four\\FourOAuthScope;
use Checkout\\Instruments\\Four\\Create\\CreateCustomerInstrumentRequest;
use Checkout\\Instruments\\Four\\Create\\CreateTokenInstrumentRequest;

// API Keys
$builder = CheckoutFourSdk::staticKeys();
$builder->setPublicKey("public_key");
$builder->setSecretKey("secret_key");
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();

// OAuth
$builder = CheckoutFourSdk::oAuth();
$builder->clientCredentials("client_id", "client_secret");
$builder->scopes([FourOAuthScope::$Gateway]); // more scopes available
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
    $request_id = $e->request_id;
    $http_status_code = $e->http_status_code;
    $error_details = $e->error_details;
} catch (CheckoutArgumentException $e) {
    // Bad arguments
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
