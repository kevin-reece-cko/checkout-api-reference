var specs = require('./specs');

specs.syncPaymentRequest('Sofort', 'https://apm-sofort.cko-sbox.ckotech.co/relations/gw/pay');
specs.syncPaymentResponse('Sofort', 'https://apm-sofort.cko-sbox.ckotech.co/relations/gw/payment');