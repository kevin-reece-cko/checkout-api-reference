<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php

use Checkout\\CheckoutSdk;
use Checkout\\Environment;
use Checkout\\OAuthScope;

$api = CheckoutSdk::builder()->oAuth()
    ->clientCredentials("client_id", "client_secret")
    ->scopes([OAuthScope::$Balances])
    ->environment(Environment::sandbox())
    ->build();