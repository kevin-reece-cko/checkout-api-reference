import { Checkout } from 'checkout-node-sdk';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['sessions:browser'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	let session = await cko.sessions.request({
		source: {
			type: 'card',
			number: '4485040371536584',
			expiry_month: 1,
			expiry_year: 2030,
		},
		amount: 100,
		currency: 'USD',
		authentication_type: 'regular',
		authentication_category: 'payment',
		challenge_indicator: 'no_preference',
		reference: 'ORD-5023-4E89',
		transaction_type: 'goods_service',
		processing_channel_id: 'pc_zs5fqhybzc2e3jmq3efvybybpq',
		shipping_address: {
			address_line1: 'Checkout.com',
			address_line2: '90 Tottenham Court Road',
			city: 'London',
			state: 'GB',
			zip: 'W1T 4TJ',
			country: 'GB',
		},
		completion: {
			type: 'non_hosted',
			callback_url: 'https://example.com/sessions/callback',
		},
	});
} catch (err) {
	console.log(err.name);
}
