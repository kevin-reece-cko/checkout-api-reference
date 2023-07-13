import { Checkout } from 'checkout-sdk-node';

let cko = new Checkout('sk_sbox_XXXXX', {
	pk: 'pk_sbox_XXXXX',
});

try {
	const apple = await cko.applePay.upload({
		content: 'XXXXXXX', // make sure you escape the characters
	});
} catch (err) {
	console.log(err.name);
}
