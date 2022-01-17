import { Checkout } from 'checkout-node-sdk';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['gateway'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	let workflows = await cko.workflows.updateAction('wf_2i7z3lwdoe5uzmomm7yzrytqdy', 'wfa_5qxwp7stgcqufj63mkr42xyeqi', {
		type: 'webhook',
		url: 'https://example.com/updated',
	});
} catch (err) {
	console.log(err.name);
}
