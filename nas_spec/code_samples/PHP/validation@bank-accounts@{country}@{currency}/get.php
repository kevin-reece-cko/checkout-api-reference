<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Common\\AccountHolderType;
use Checkout\\Common\\Country;
use Checkout\\Common\\Currency;
use Checkout\\Environment;
use Checkout\\Instruments\\Get\\BankAccountFieldQuery;
use Checkout\\Instruments\\Get\\PaymentNetwork;
use Checkout\\OAuthScope;

$api = CheckoutSdk::builder()->oAuth()
    ->clientCredentials("client_id", "client_secret")
    ->scopes([OAuthScope::$PayoutsBankDetails])
    ->environment(Environment::sandbox())
    ->build();

$request = new BankAccountFieldQuery();
$request->payment_network = PaymentNetwork::$local;
$request->account_holder_type = AccountHolderType::$individual;

try {
    $response = $api->getInstrumentsClient()->getBankAccountFieldFormatting(Country::$GB, Currency::$GBP, $request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}