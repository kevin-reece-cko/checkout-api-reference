import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

const event = await cko.events.retrieveEvent('evt_c2qelfixai2u3es3ksovngkx3e');
