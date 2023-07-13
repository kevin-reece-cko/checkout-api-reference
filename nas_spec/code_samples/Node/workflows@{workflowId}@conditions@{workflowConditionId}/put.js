import { Checkout } from 'checkout-sdk-node';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['gateway'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	let workflows = await cko.workflows.updateCondition(
		'wf_2i7z3lwdoe5uzmomm7yzrytqdy',
		'wfc_ybu4t6aruwju5l6ymlc67ya5ne',
		{
			type: 'event',
			events: {
				gateway: ['card_verification_declined', 'card_verified', 'payment_approved'],
			},
		}
	);
} catch (err) {
	console.log(err.name);
}
