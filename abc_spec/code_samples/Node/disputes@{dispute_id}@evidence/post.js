import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

const submitEvidence = await cko.disputes.submit('dsp_bc94ebda8d275i461229');
