<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Common\\ChallengeIndicatorType;
use Checkout\\Common\\Country;
use Checkout\\Common\\Currency;
use Checkout\\Common\\Phone;
use Checkout\\Environment;
use Checkout\\OAuthScope;
use Checkout\\Sessions\\Category;
use Checkout\\Sessions\\Channel\\AppSession;
use Checkout\\Sessions\\Channel\\SdkEphemeralPublicKey;
use Checkout\\Sessions\\Channel\\SdkInterfaceType;
use Checkout\\Sessions\\Completion\\NonHostedCompletionInfo;
use Checkout\\Sessions\\SessionAddress;
use Checkout\\Sessions\\SessionMarketplaceData;
use Checkout\\Sessions\\SessionRequest;
use Checkout\\Sessions\\SessionsBillingDescriptor;
use Checkout\\Sessions\\Source\\SessionCardSource;
use Checkout\\Sessions\\TransactionType;
use Checkout\\Sessions\\UIElements;

$api = CheckoutSdk::builder()->oAuth()
    ->clientCredentials("client_id", "client_secret")
    ->scopes([OAuthScope::$SessionsApp, OAuthScope::$SessionsBrowser])
    ->environment(Environment::sandbox())
    ->build();

$phone = new Phone();
$phone->country_code = "+1";
$phone->number = "415 555 2671";

$billingAddress = new SessionAddress();
$billingAddress->address_line1 = "CheckoutSdk.com";
$billingAddress->address_line2 = "90 Tottenham Court Road";
$billingAddress->city = "London";
$billingAddress->state = "ENG";
$billingAddress->country = Country::$GB;

$sessionCardSource = new SessionCardSource();
$sessionCardSource->billing_address = $billingAddress;
$sessionCardSource->number = "number";
$sessionCardSource->expiry_month = 10;
$sessionCardSource->expiry_year = 2026;
$sessionCardSource->name = "Name";
$sessionCardSource->email = "email@docs.checkout.com";
$sessionCardSource->home_phone = $phone;
$sessionCardSource->work_phone = $phone;
$sessionCardSource->mobile_phone = $phone;

$shippingAddress = new SessionAddress();
$shippingAddress->address_line1 = "CheckoutSdk.com";
$shippingAddress->address_line2 = "90 Tottenham Court Road";
$shippingAddress->city = "London";
$shippingAddress->state = "London";
$shippingAddress->zip = "W1T 4TJ";
$shippingAddress->country = Country::$GB;

$marketPlaceData = new SessionMarketplaceData();
$marketPlaceData->sub_entity_id = "ent_123456789";

$billingDescriptor = new SessionsBillingDescriptor();
$billingDescriptor->name = "Name";

$nonHostedCompletionInfo = new NonHostedCompletionInfo();
$nonHostedCompletionInfo->callback_url = "https://docs.checkout.com/callback";

$sdkEphemeralPublicKey = new SdkEphemeralPublicKey();
$sdkEphemeralPublicKey->kty = "EC";
$sdkEphemeralPublicKey->crv = "P-256";
$sdkEphemeralPublicKey->x = "f83OJ3D2xF1Bg8vub9tLe1gHMzV76e8Tus9uPHvRVEU";
$sdkEphemeralPublicKey->y = "x_FEzRu9m36HLN_tue659LNpXW6pCyStikYjKIWI5a0";

$appSession = new AppSession();
$appSession->sdk_app_id = "dbd64fcb-c19a-4728-8849-e3d50bfdde39";
$appSession->sdk_max_timeout = 5;
$appSession->sdk_encrypted_data = "{}";
$appSession->sdk_ephem_pub_key = $sdkEphemeralPublicKey;
$appSession->sdk_reference_number = "3DS_LOA_SDK_PPFU_020100_00007";
$appSession->sdk_transaction_id = "b2385523-a66c-4907-ac3c-91848e8c0067";
$appSession->sdk_interface_type = SdkInterfaceType::$both;
$appSession->sdk_ui_elements = array(UIElements::$single_select, UIElements::$html_other);

$request = new SessionRequest();
$request->source = $sessionCardSource;
$request->amount = 100;
$request->currency = Currency::$USD;
$request->processing_channel_id = "pc_123456789";
$request->marketplace = $marketPlaceData;
$request->authentication_category = Category::$payment;
$request->challenge_indicator = ChallengeIndicatorType::$no_preference;
$request->billing_descriptor = $billingDescriptor;
$request->reference = "reference";
$request->transaction_type = TransactionType::$goods_service;
$request->shipping_address = $shippingAddress;
$request->completion = $nonHostedCompletionInfo;
$request->channel_data = $appSession;

try {
    $response = $api->getSessionsClient()->requestSession($request);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}