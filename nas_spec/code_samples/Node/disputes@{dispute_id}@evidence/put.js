import { Checkout } from 'checkout-node-sdk';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['gateway'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	const evidence = await cko.disputes.provideEvidence('dsp_bc94ebda8d275i461229', {
		proof_of_delivery_or_service_text: 'http://checkout.com/document.pdf',
	});
} catch (err) {
	console.log(err.name);
}
