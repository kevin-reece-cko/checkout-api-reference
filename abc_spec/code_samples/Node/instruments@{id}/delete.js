import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

try {
	const deleteOutcome = await cko.instruments.delete(instrument.id);
} catch (err) {
	console.log(err.name);
}
