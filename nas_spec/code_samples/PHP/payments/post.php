// Please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutFourSdk;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\Currency;
use Checkout\\Common\\CustomerRequest;
use Checkout\\Common\\Phone;
use Checkout\\Environment;
use Checkout\\Four\\FourOAuthScope;
use Checkout\\Payments\\Four\\Request\\PaymentRequest;
use Checkout\\Payments\\Four\\Request\\Source\\RequestCardSource;
use Checkout\\Payments\\Four\\Sender\\Identification;
use Checkout\\Payments\\Four\\Sender\\IdentificationType;
use Checkout\\Payments\\Four\\Sender\\PaymentIndividualSender;

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

$requestCardSource = new RequestCardSource();
$requestCardSource->name = "Name";
$requestCardSource->number = "Number";
$requestCardSource->expiry_year = 2026;
$requestCardSource->expiry_month = 10;
$requestCardSource->cvv = "123";
$requestCardSource->billing_address = $address;
$requestCardSource->phone = $phone;

$customerRequest = new CustomerRequest();
$customerRequest->email = "email@docs.checkout.com";
$customerRequest->name = "Customer";

$identification = new Identification();
$identification->issuing_country = Country::$GT;
$identification->number = "1234";
$identification->type = IdentificationType::$drivingLicence;

$paymentIndividualSender = new PaymentIndividualSender();
$paymentIndividualSender->fist_name = "FirstName";
$paymentIndividualSender->last_name = "LastName";
$paymentIndividualSender->address = $address;
$paymentIndividualSender->identification = $identification;

$request = new PaymentRequest();
$request->source = $requestCardSource;
$request->capture = true;
$request->reference = "reference";
$request->amount = 10;
$request->currency = Currency::$USD;
$request->customer = $customerRequest;
$request->sender = $paymentIndividualSender;

try {
    $response = $api->getPaymentsClient()->requestPayment($request);
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
