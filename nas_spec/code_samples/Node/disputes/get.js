import { Checkout } from 'checkout-node-sdk';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['gateway'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	const disputes = await cko.disputes.get({
		limit: 5,
		id: 'dsp_bc94ebda8d275i461229',
	});
} catch (err) {
	console.log(err.name);
}
