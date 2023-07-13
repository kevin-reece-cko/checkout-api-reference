<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Common\\Currency;
use Checkout\\Environment;
use Checkout\\Forex\\QuoteRequest;
use Checkout\\OAuthScope;

$api = CheckoutSdk::builder()->oAuth()
    ->clientCredentials("client_id", "client_secret")
    ->scopes([OAuthScope::$Fx])
    ->environment(Environment::sandbox())
    ->build();

$request = new QuoteRequest();
$request->source_currency = Currency::$GBP;
$request->source_amount = 30000;
$request->destination_currency = Currency::$USD;
$request->process_channel_id = "pc_abcdefghijklmnopqrstuvwxyz";

try {
    $response = $api->getForexClient()->requestQuote($request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}