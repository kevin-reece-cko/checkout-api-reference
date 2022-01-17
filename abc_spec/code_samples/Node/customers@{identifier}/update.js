import { Checkout } from 'checkout-node-sdk';

const cko = new Checkout('sk_XXXX');

try {
	const customerResponse = await cko.customers.get('cus_2tvaccfvs3lulevzg42vgyvtdq');
} catch (err) {
	console.log(err.name);
}
