import { Checkout } from 'checkout-node-sdk';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['sessions:browser'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	let updated = await cko.sessions.update3DSMethodCompletionIndicator('sid_aurdb2b3yv6eniu7mbrl7nfopm', 'U');
} catch (err) {
	console.log(err.name);
}
