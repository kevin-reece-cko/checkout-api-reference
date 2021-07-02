import { Checkout } from 'checkout-node-sdk';

const cko = new Checkout('sk_XXXX');

const payment = await cko.payments.get('pl_irx_SMlY5RCA');
