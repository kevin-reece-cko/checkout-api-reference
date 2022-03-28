// For more information please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutDefaultSdk;
use Checkout\\Environment;

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

$cardSourcePrism = new CardSourcePrism();
$cardSourcePrism->billing_address = $address;
$cardSourcePrism->expiry_month = 10;
$cardSourcePrism->expiry_year = 2027;
$cardSourcePrism->number = "number";

$customerRequest = new CustomerRequest();
$customerRequest->email = "email@docs.checkout.com";
$customerRequest->name = "Name";

$riskPayment = new RiskPayment();
$riskPayment->psp = "psp";
$riskPayment->id = "78453878";

$riskShippingDetails = new RiskShippingDetails();
$riskShippingDetails->address = $address;

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

$request = new PreAuthenticationAssessmentRequest();
$request->date = new DateTime();
$request->source = $cardSourcePrism;
$request->customer = $customerRequest;
$request->payment = $riskPayment;
$request->shipping = $riskShippingDetails;
$request->reference = "ORD-1011-87AH";
$request->description = "Set of 3 masks";
$request->amount = 10;
$request->currency = Currency::$GBP;
$request->device = $device;
$request->metadata = array("VoucherCode" => "loyalty_10", "discountApplied" => "10", "customer_id" => "2190EF321");

try {
    $response = $api->getRiskClient()->requestPreAuthenticationRiskScan($request);
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
