import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

const payment = await cko.payments.get('pay_je5hbbb4u3oe7k4u3lbwlu3zkq');
