import { Checkout } from 'checkout-node-sdk';

const cko = new Checkout('sk_XXXX');

try {
	const instrumentResponse = await cko.instruments.update('src_udfsqsgpjykutgs26fiejgizau', {
		expiry_year: 2030,
	});
} catch (err) {
	console.log(err.name);
}
