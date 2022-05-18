import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

try {
	const hosted = await cko.hostedPayments.get('hpp_kQhs_fI9b8oQ');
} catch (err) {
	console.log(err.name);
}
