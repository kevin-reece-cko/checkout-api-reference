<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Environment;
use Checkout\\OAuthScope;
use Checkout\\Workflows\\Actions\\WebhookSignature;
use Checkout\\Workflows\\Actions\\WebhookWorkflowActionRequest;

//API Keys
$api = CheckoutSdk::builder()->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

//OAuth
$api = CheckoutSdk::builder()->oAuth()
    ->clientCredentials("client_id", "client_secret")
    ->scopes([OAuthScope::$Flow])
    ->environment(Environment::sandbox())
    ->build();

$webhookSignature = new WebhookSignature();
$webhookSignature->method = "HMACSHA256";
$webhookSignature->key = "public-signing-key";

$request = new WebhookWorkflowActionRequest();
$request->url = "workflow_name";
$request->signature = "workflow_name";

try {
    $response = $api->getWorkflowsClient()->updateWorkflowAction("workflow_id", "action_id", $request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}