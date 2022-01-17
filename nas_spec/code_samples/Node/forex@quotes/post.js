import { Checkout } from 'checkout-node-sdk';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['gateway'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	let forex = await cko.forex.request({
		source_currency: 'GBP',
		source_amount: 30000,
		destination_currency: 'USD',
		processing_channel_id: 'pc_zs5fqhybzc2e3jmq3efvybybpq',
	});
} catch (err) {
	console.log(err.name);
}
