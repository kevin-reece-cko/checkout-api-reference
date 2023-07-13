import { Checkout } from 'checkout-sdk-node';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['gateway'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	let workflows = await cko.workflows.reflowEventsByEventAndWorkflowIds(
		['evt_hsfxtjwidv6ulah5gdbiqwqnka'],
		['wf_6p73pesh75vu7fqo6p6exhpe54']
	);
} catch (err) {
	console.log(err.name);
}
