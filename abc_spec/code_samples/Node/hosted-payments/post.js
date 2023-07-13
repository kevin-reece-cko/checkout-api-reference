import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

try {
	const hosted = await cko.hostedPayments.create({
		amount: 10,
		currency: 'USD',
		billing: {
			address: {
				address_line1: 'Checkout.com',
				address_line2: '90 Tottenham Court Road',
				city: 'London',
				state: 'London',
				zip: 'W1T 4TJ',
				country: 'GB',
			},
		},
		success_url: 'https://example.com/success',
		cancel_url: 'https://example.com/cancel',
		failure_url: 'https://example.com/failure',
	});
} catch (err) {
	console.log(err.name);
}
