import { Checkout } from 'checkout-sdk-node';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['sessions:browser'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use API keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	let getSession = await cko.sessions.get('sid_jlfm4ithpgpefdxgzzdnc3xrc4');
} catch (err) {
	console.log(err.name);
}
