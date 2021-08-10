import { Checkout } from 'checkout-node-sdk';

const cko = new Checkout('sk_XXXX');

const payment = await cko.paymentsLinks.get('pl_irx_SsMlY5RCA');
