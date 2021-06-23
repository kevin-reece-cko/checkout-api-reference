import { Checkout } from 'checkout-node-sdk';

const cko = new Checkout('sk_XXXX');

try {
	const linksResponse = await cko.paymentLinks.create({
		amount: 10359,
		currency: 'EUR',
		billing: {
			address: {
				country: 'DE',
			},
		},
		products: [
			{
				name: 'Moonlight blue lightsaber',
				quantity: 2,
				price: 3999,
			},
			{
				name: 'Stainless steel watch strap',
				quantity: 1,
				price: 2361,
			},
		],
		return_url: 'https://pay.sandbox.checkout.com/link/examples/docs',
	});
} catch (err) {
	console.log(err.name);
}
