import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

const getEvidence = await cko.disputes.getEvidence('dsp_bc94ebda8d275i461229');
