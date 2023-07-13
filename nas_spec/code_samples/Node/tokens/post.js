import { Checkout } from 'checkout-sdk-node';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['gateway'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	const token = await cko.tokens.request({
		// infered type: "applepay"
		token_data: {
			version: 'EC_v1',
			data: 't7GeajLB9skXB6QSWfEpPA4WPhDqB7ekdd+F7588arLzve...',
			signature: 'MIAGCSqGbGUg...',
			header: {
				ephemeralPublicKey: 'MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgA...',
				publicKeyHash: 'tqYV+tmG9aMh+l/K6cicUnPqkb1gUiLjSTM9gEz6...',
				transactionId: '3cee89679130a4b...',
			},
		},
	});
} catch (err) {
	console.log(err.name);
}
