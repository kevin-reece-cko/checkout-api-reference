<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Environment;
use Checkout\\Webhooks\\Previous\\WebhookRequest;

$api = CheckoutSdk::builder()
    ->previous()
    ->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

$request = new WebhookRequest();
$request->url = "https://test.checkout.com/webhooks/changed";
$request->headers = [];
$request->content_type = "json";
$request->event_types = array("source_updated");
$request->active = true;

try {
    $response = $api->getWebhooksClient()->patchWebhook("webhookId", $request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
