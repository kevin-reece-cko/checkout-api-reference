import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

try {
	const customerResponse = await cko.customers.delete('cus_zbgrqmm6s5ne7lszegj5iu4lci');
} catch (err) {
	console.log(err.name);
}
