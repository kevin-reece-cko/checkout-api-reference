import { Checkout } from 'checkout-node-sdk';

const cko = new Checkout('sk_XXXX');

const payment = await cko.paymentLinks.get('pl_XXXX');
