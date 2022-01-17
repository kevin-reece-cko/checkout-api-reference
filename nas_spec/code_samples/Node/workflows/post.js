import { Checkout } from 'checkout-node-sdk';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['gateway'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	let workflows = await cko.workflows.add({
		name: 'Webhooks workflow',
		conditions: [
			{
				type: 'event',
				events: {
					gateway: ['payment_approved', 'payment_declined'],
				},
			},
			{
				type: 'entity',
				entities: ['ent_djigcqx4clmufo2sasgomgpqsq'],
			},
			{
				type: 'processing_channel',
				processing_channels: ['pc_zs5fqhybzc2e3jmq3efvybybpq'],
			},
		],
		actions: [
			{
				type: 'webhook',
				url: 'https://example.com/webhooks',
				headers: {
					Authorization: '70ed20ff-ba31-4ea3-b3ef-772f2be1cbdf',
				},
				signature: {
					method: 'HMACSHA256',
					key: '8V8x0dLK%AyD*DNS8JJr',
				},
			},
		],
	});
} catch (err) {
	console.log(err.name);
}
