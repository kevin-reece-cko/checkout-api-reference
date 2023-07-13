import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

const webhooks = await cko.webhooks.retrieveWebhooks();
