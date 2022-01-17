import { Checkout } from 'checkout-node-sdk';

const cko = new Checkout();

try {
	const access_token = await cko.access.request({
		grant_type: 'client_credentials',
		client_id: 'ack_XXXX',
		client_secret: 'XXXXX',
		scope: 'gateway',
	});
} catch (err) {
	console.log(err.name);
}
