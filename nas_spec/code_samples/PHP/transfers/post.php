<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Environment;
use Checkout\\OAuthScope;
use Checkout\\Transfers\\CreateTransferRequest;
use Checkout\\Transfers\\TransferDestination;
use Checkout\\Transfers\\TransferSource;
use Checkout\\Transfers\\TransferType;

$api = CheckoutSdk::builder()->oAuth()
    ->clientCredentials("client_id", "client_secret")
    ->scopes([OAuthScope::$Transfers])
    ->environment(Environment::sandbox())
    ->build();

$transferSource = new TransferSource();
$transferSource->id = "ent_kidtcgc3ge5unf4a5i6enhnr5m";
$transferSource->amount = 100;

$transferDestination = new TransferDestination();
$transferDestination->id = "ent_w4jelhppmfiufdnatam37wrfc4";

$request = new CreateTransferRequest();
$request->transfer_type = TransferType::$commission;
$request->source = $transferSource;
$request->destination = $transferDestination;

try {
    $response = $api->getTransfersClient()->initiateTransferOfFunds($request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}