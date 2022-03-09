// Please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutApiException;
use Checkout\\CheckoutArgumentException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutFourSdk;
use Checkout\\Environment;
use Checkout\\Four\\FourOAuthScope;

$builder = CheckoutFourSdk::oAuth();
$builder->clientCredentials("client_id", "client_secret");
$builder->scopes([FourOAuthScope::$PayoutsBankDetails]); // more scopes available
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$builder->setFilesEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();

$request = new BankAccountFieldQuery();
$request->payment_network = PaymentNetwork::$local;
$request->account_holder_type = AccountHolderType::$individual;

try {
    $response = $api->getInstrumentsClient()->getBankAccountFieldFormatting(Country::$GB, Currency::$GBP, $request);
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
