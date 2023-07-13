import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

const webhook = await cko.webhooks.removeWebhook('wh_ahun3lg7bf4e3lohbhni65335u');
