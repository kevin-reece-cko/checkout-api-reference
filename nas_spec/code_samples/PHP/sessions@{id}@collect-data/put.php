// Please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutFourSdk;
use Checkout\\Environment;
use Checkout\\Four\\FourOAuthScope;
use Checkout\\Sessions\\Channel\\BrowserSession;
use Checkout\\Sessions\\Channel\\ThreeDsMethodCompletion;

$builder = CheckoutFourSdk::oAuth();
$builder->clientCredentials("client_id", "client_secret");
$builder->scopes([FourOAuthScope::$SessionsApp, FourOAuthScope::$SessionsBrowser]); // more scopes available
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$builder->setFilesEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();

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
    $request_id = $e->request_id;
    $http_status_code = $e->http_status_code;
    $error_details = $e->error_details;
} catch (CheckoutArgumentException $e) {
    // Bad arguments
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}

