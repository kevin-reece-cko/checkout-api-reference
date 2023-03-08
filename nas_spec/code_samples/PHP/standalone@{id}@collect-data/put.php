<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Environment;
use Checkout\\OAuthScope;
use Checkout\\Sessions\\Channel\\BrowserSession;
use Checkout\\Sessions\\Channel\\ThreeDsMethodCompletion;

$api = CheckoutSdk::builder()->oAuth()
    ->clientCredentials("client_id", "client_secret")
    ->scopes([OAuthScope::$SessionsApp, OAuthScope::$SessionsBrowser])
    ->environment(Environment::sandbox())
    ->build();

$browserSession = new BrowserSession();
$browserSession->accept_header = "Accept:  *.*, q=0.1";
$browserSession->java_enabled = true;
$browserSession->language = "FR-fr";
$browserSession->color_depth = "16";
$browserSession->screen_width = "1920";
$browserSession->screen_height = "1080";
$browserSession->timezone = "60";
$browserSession->user_agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36";
$browserSession->three_ds_method_completion = ThreeDsMethodCompletion::$y;
$browserSession->ip_address = "1.12.123.255";

try {
    $response = $api->getSessionsClient()->updateSession("session_id", $browserSession);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}