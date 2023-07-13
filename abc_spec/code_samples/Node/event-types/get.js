import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

const events = await cko.events.retrieveEventTypes();
