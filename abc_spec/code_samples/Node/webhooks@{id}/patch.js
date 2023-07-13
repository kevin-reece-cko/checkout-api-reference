import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

const webhook = await cko.webhooks.partiallyUpdateWebhook('wh_ahun3lg7bf4e3lohbhni65335u', {
	url: 'https://example.com/webhooks/updated',
});
