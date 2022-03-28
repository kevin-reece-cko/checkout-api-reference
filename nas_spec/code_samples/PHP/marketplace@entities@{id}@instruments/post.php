// Please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutFourSdk;
use Checkout\\Common\\Country;
use Checkout\\Common\\Currency;
use Checkout\\Common\\Four\\AccountType;
use Checkout\\Common\\Four\\BankDetails;
use Checkout\\Environment;
use Checkout\\Four\\FourOAuthScope;
use Checkout\\Marketplace\\InstrumentDocument;
use Checkout\\Marketplace\\MarketplacePaymentInstrument;

// OAuth
$builder = CheckoutFourSdk::oAuth();
$builder->clientCredentials("client_id", "client_secret");
$builder->scopes([FourOAuthScope::$Marketplace]); // more scopes available
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$builder->setFilesEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();

$request = new MarketplacePaymentInstrument();
$request->account_number = "123456789";
$request->account_type = AccountType::$cash;
$request->bank = new BankDetails();
$request->bank_code = "bank_code";
$request->bban = "bban";
$request->branch_code = "123";
$request->country = Country::$GB;
$request->currency = Currency::$GBP;
$request->document = new InstrumentDocument();
$request->iban = "iban";
$request->label = "mkt-1";
$request->swift_bic = "BIC";

try {
    $api->getMarketplaceClient()->createPaymentInstrument("entity_id", $request);
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
