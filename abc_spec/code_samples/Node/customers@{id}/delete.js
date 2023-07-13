import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

try {
	const customerResponse = await cko.customers.delete('cus_2tvaccfvs3lulevzg42vgyvtdq');
} catch (err) {
	console.log(err.name);
}
