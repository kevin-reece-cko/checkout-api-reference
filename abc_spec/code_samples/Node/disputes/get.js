import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

const disputes = await cko.disputes.get({
	limit: 5,
	id: 'dsp_bc94ebda8d275i461229',
});
