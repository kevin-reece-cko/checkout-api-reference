import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

const webhook = await cko.webhooks.retrieveWebhook('wh_tdt72zlbe7cudogxdgit6nwk6i');
