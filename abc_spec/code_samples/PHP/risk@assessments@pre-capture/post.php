<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\Currency;
use Checkout\\Common\\CustomerRequest;
use Checkout\\Environment;
use Checkout\\Risk\\Device;
use Checkout\\Risk\\Location;
use Checkout\\Risk\\PreCapture\\AuthenticationResult;
use Checkout\\Risk\\PreCapture\\AuthorizationResult;
use Checkout\\Risk\\PreCapture\\PreCaptureAssessmentRequest;
use Checkout\\Risk\\RiskPayment;
use Checkout\\Risk\\RiskShippingDetails;
use Checkout\\Risk\\Source\\CardSourcePrism;

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

$location = new Location();
$location->latitude = "51.5107";
$location->longitude = "0.1313";

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
$request->source = new CardSourcePrism();
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
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
