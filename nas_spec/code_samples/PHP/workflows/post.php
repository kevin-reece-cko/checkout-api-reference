<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Environment;
use Checkout\\OAuthScope;
use Checkout\\Workflows\\Actions\\WebhookSignature;
use Checkout\\Workflows\\Actions\\WebhookWorkflowActionRequest;
use Checkout\\Workflows\\Conditions\\EntityWorkflowConditionRequest;
use Checkout\\Workflows\\Conditions\\EventWorkflowConditionRequest;
use Checkout\\Workflows\\Conditions\\ProcessingChannelWorkflowConditionRequest;
use Checkout\\Workflows\\CreateWorkflowRequest;

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

$signature = new WebhookSignature();
$signature->key = "8V8x0dLK%AyD*DNS8JJr";
$signature->method = "HMACSHA256";

$actionRequest = new WebhookWorkflowActionRequest();
$actionRequest->url = "https://google.com/fail";
$actionRequest->signature = $signature;

$entityWorkflowConditionRequest = new EntityWorkflowConditionRequest();
$entityWorkflowConditionRequest->entities = ["entity_id"];

$eventWorkflowConditionRequest = new EventWorkflowConditionRequest();
$eventWorkflowConditionRequest->events = [
    "gateway" => ["payment_approved",
        "payment_declined",
        "card_verification_declined",
        "card_verified",
        "payment_authorization_incremented",
        "payment_authorization_increment_declined",
        "payment_capture_declined",
        "payment_captured",
        "payment_refund_declined",
        "payment_refunded",
        "payment_void_declined",
        "payment_voided"],
    "dispute" => ["dispute_canceled",
        "dispute_evidence_required",
        "dispute_expired",
        "dispute_lost",
        "dispute_resolved",
        "dispute_won"]
];

$processingChannelWorkflowConditionRequest = new ProcessingChannelWorkflowConditionRequest();
$processingChannelWorkflowConditionRequest->processing_channels = ["pc_sdsdsdwdewdsdds"];

$request = new CreateWorkflowRequest();
$request->actions = [$actionRequest];
$request->conditions = [$entityWorkflowConditionRequest, $eventWorkflowConditionRequest,
    $processingChannelWorkflowConditionRequest];
$request->name = "Workflow";
$request->active = true;

try {
    $response = $api->getWorkflowsClient()->createWorkflow($request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}