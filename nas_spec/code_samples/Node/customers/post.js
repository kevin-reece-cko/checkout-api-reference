import { Checkout } from 'checkout-sdk-node';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['gateway'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

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
