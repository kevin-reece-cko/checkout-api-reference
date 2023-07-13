import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

const disputeDetails = await cko.disputes.getDetails('dsp_bc94ebda8d275i461229');
