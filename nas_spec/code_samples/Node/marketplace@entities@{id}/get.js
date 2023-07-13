import { Checkout } from 'checkout-sdk-node';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['marketplace'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	let marketplace = await cko.marketplace.getSubEntityDetails('ent_aneh5mtyobxzazriwuevngrz6y');
} catch (err) {
	console.log(err.name);
}
