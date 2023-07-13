import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

const retryAll = await cko.events.retryAll('evt_c2qelfixai2u3es3ksovngkx3e');
