import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

try {
	const risk = await cko.risk.requestPreCapture({
		source: {
			type: 'token',
			token: 'tok_XXX',
		},
	});
} catch (err) {
	console.log(err.name);
}
