import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

try {
	const instrument = await cko.instruments.get('src_udfsqsgpjykutgs26fiejgizau');
} catch (err) {
	console.log(err.name);
}
