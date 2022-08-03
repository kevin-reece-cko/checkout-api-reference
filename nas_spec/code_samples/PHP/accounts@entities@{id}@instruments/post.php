<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\Accounts\\AccountsPaymentInstrument;
use Checkout\\Accounts\\InstrumentDocument;
use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Common\\AccountHolder;
use Checkout\\Common\\Address;
use Checkout\\Common\\Country;
use Checkout\\Common\\Currency;
use Checkout\\Environment;
use Checkout\\OAuthScope;

$api = CheckoutSdk::builder()->oAuth()
    ->clientCredentials("client_id", "client_secret")
    ->scopes([OAuthScope::$Accounts])
    ->environment(Environment::sandbox())
    ->build();

$address = new Address();
$address->address_line1 = "CheckoutSdk.com";
$address->address_line2 = "90 Tottenham Court Road";
$address->city = "London";
$address->state = "London";
$address->zip = "W1T 4TJ";
$address->country = Country::$GB;

$accountHolder = new AccountHolder();
$accountHolder->first_name = "FirstName";
$accountHolder->last_name = "LastName";
$accountHolder->billing_address = $address;

$instrumentDocument = new InstrumentDocument();
$instrumentDocument->type = "bank_statement";
$instrumentDocument->file_id = "file_wxglze3wwywujg4nna5fb7ldli";

$request = new AccountsPaymentInstrument();
$request->label = "Peter's Personal Account";
$request->account_number = "12345678";
$request->bank_cod = "050389";
$request->currency = Currency::$GBP;
$request->country = Country::$GB;
$request->account_holder = $accountHolder;
$request->document = $instrumentDocument;


try {
    $response = $api->getAccountsClient()->createPaymentInstrument("entity_id", $request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}