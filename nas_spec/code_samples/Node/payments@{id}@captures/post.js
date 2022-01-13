import { Checkout } from 'checkout-node-sdk';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['gateway'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	const payment = await cko.payments.capture('pay_je5hbbb4u3oe7k4u3lbwlu3zkq', {
		amount: 1000,
		reference: 'CAPTURE ORDER 1234',
		metadata: {
			value: 'my value',
		},
	});
} catch (err) {
	console.log(err.name);
}
