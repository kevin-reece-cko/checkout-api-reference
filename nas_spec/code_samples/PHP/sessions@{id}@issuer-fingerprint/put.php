<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Environment;
use Checkout\\OAuthScope;
use Checkout\\Sessions\\Channel\\ThreeDsMethodCompletion;
use Checkout\\Sessions\\ThreeDsMethodCompletionRequest;

$api = CheckoutSdk::builder()->oAuth()
    ->clientCredentials("client_id", "client_secret")
    ->scopes([OAuthScope::$SessionsApp, OAuthScope::$SessionsBrowser])
    ->environment(Environment::sandbox())
    ->build();

$request = new ThreeDsMethodCompletionRequest();
$request->three_ds_method_completion = ThreeDsMethodCompletion::$y;

try {
    $api->getSessionsClient()->updateThreeDsMethodCompletionIndicator("session_id", $request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}