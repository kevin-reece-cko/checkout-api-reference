// Please refer to https://github.com/checkout/checkout-sdk-php
<?php

use Checkout\\CheckoutFourSdk;
use Checkout\\Environment;
use Checkout\\Four\\FourOAuthScope;

$builder = CheckoutFourSdk::staticKeys();
$builder->setPublicKey("public_key");
$builder->setSecretKey("secret_key");
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();

$builder = CheckoutFourSdk::oAuth();
$builder->clientCredentials("client_id", "client_secret");
$builder->scopes([FourOAuthScope::$Files, FourOAuthScope::$Flow, FourOAuthScope::$Fx, FourOAuthScope::$Gateway,
    FourOAuthScope::$Marketplace, FourOAuthScope::$SessionsApp, FourOAuthScope::$SessionsBrowser,
    FourOAuthScope::$Vault, FourOAuthScope::$PayoutsBankDetails]); // more scopes available
$builder->setEnvironment(Environment::sandbox()); // or Environment::production()
$builder->setFilesEnvironment(Environment::sandbox()); // or Environment::production()
$api = $builder->build();