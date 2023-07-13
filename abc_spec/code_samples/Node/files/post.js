import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

const file = await cko.files.upload({
	path: fs.createReadStream('./test/files/evidence.jpg'),
	purpose: 'dispute_evidence',
});
