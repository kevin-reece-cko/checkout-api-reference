// Please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutFourSdk;
use Checkout\\Common\\Currency;
use Checkout\\Environment;

// API Keys
$builder = CheckoutFourSdk::staticKeys();
$builder->setPublicKey("public_key");
$builder->setSecretKey("secret_key");
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();

$builder = CheckoutDefaultSdk::staticKeys();
$builder->setPublicKey("public_key");
$builder->setSecretKey("secret_key");
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();

$customerRequest = new CustomerRequest();
$customerRequest->email = "email@docs.checkout.com";
$customerRequest->name = "name";

$riskPayment = new RiskPayment();
$riskPayment->psp = "psp";
$riskPayment->id = "78453878";

$riskShippingDetails = new RiskShippingDetails();
$riskShippingDetails->address = $address;

$authenticationResult = new AuthenticationResult();
$authenticationResult->attempted = true;
$authenticationResult->challenged = true;
$authenticationResult->liability_shifted = true;
$authenticationResult->method = "3ds";
$authenticationResult->succeeded = true;
$authenticationResult->version = "2.0";

$authorizationResult = new AuthorizationResult();
$authorizationResult->avs_code = "V";
$authorizationResult->cvv_result = "N";

$device = new Device();
$device->location = $location;
$device->type = "Phone";
$device->os = "ISO";
$device->model = "iPhone X";
$device->date = new DateTime();
$device->user_agent = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1";
$device->fingerprint = "34304a9e3fg09302";

$request = new PreCaptureAssessmentRequest();
$request->date = new DateTime();
$request->source = $requestSource;
$request->customer = $customerRequest;
$request->payment = $riskPayment;
$request->shipping = $riskShippingDetails;
$request->amount = 6540;
$request->currency = Currency::$GBP;
$request->device = $device;
$request->metadata = array("VoucherCode" => "loyalty_10", "discountApplied" => "10", "customer_id" => "2190EF321");
$request->authentication_result = $authenticationResult;
$request->authorization_result = $authorizationResult;

try {
    $response = $api->getRiskClient()->requestPreCaptureRiskScan($request);
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
