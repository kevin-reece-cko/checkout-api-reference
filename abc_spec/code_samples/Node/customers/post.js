import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

try {
	const customerResponse = await cko.customers.create({
		email: 'JohnTest@test.com',
		name: 'John Test',
		phone: {
			country_code: '+1',
			number: '4155552671',
		},
		metadata: {
			coupon_code: 'NY2018',
			partner_id: 123989,
		},
	});
} catch (err) {
	console.log(err.name);
}
