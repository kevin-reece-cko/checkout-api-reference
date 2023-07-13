import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

try {
	const events = await cko.events.retrieveEvents({
		reference: 'ORD-5023-4E89',
	});
} catch (err) {
	console.log(err.name);
}
