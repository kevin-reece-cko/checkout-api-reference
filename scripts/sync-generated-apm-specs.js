// To run this script use the command 'npm run sync-generated-apm-specs' on the IRE VPN Profile.

var specs = require('./sync-specs');

specs.syncPaymentRequest('Sofort', 'https://apm-sofort.cko-sbox.ckotech.co/relations/gw/pay');
specs.syncPaymentResponse('Sofort', 'https://apm-sofort.cko-sbox.ckotech.co/relations/gw/payment');

specs.syncPaymentRequest('QPay', 'https://apm-qpay.cko-sbox.ckotech.co/relations/gw/pay');
specs.syncPaymentResponse('QPay', 'https://apm-qpay.cko-sbox.ckotech.co/relations/gw/payment');

specs.syncPaymentRequest('Benefit', 'https://apm-benefit.cko-qa.ckotech.co/relations/gw/pay');
specs.syncPaymentResponse('Benefit', 'https://apm-benefit.cko-qa.ckotech.co/relations/gw/payment');

specs.syncPaymentRequest('Klarna', 'https://apm-klarna-v2.cko-qa.ckotech.co/relations/gw/pay');
specs.syncPaymentResponse('Klarna', 'https://apm-klarna-v2.cko-qa.ckotech.co/relations/gw/payment');