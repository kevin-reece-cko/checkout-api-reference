import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

const payment = await cko.paymentLinks.get('pl_XXXX');
