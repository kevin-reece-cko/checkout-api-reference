// Please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutFourSdk;
use Checkout\\Common\\Currency;
use Checkout\\Environment;
use Checkout\\Forex\\QuoteRequest;
use Checkout\\Four\\FourOAuthScope;

// OAuth
$builder = CheckoutFourSdk::oAuth();
$builder->clientCredentials("client_id", "client_secret");
$builder->scopes([FourOAuthScope::$Fx]); // more scopes available
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$builder->setFilesEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();

$request = new QuoteRequest();
$request->source_currency = Currency::$GBP;
$request->source_amount = 30000;
$request->destination_currency = Currency::$USD;
$request->process_channel_id = "pc_abcdefghijklmnopqrstuvwxyz";

try {
    $response = $api->getForexClient()->requestQuote(request);
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
