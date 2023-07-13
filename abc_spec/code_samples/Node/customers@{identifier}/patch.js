import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

try {
	const customerResponse = await cko.customers.update('cus_2tvaccfvs3lulevzg42vgyvtdq', {
		name: 'James Bond',
	});
} catch (err) {
	console.log(err.name);
}
