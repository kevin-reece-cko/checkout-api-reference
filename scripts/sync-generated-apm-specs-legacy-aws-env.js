// To run this script use the command 'npm run sync-generated-apm-specs-legacy-aws-env' on the LON VPN Profile. 

var specs = require('./sync-specs');

specs.syncPaymentRequest('Giropay', 'http://sb-gateway-internal.cko.lon/giropay-internal/giropay/relations/gw/pay');
specs.syncPaymentResponse('Giropay', 'http://sb-gateway-internal.cko.lon/giropay-internal/giropay/relations/gw/payment');
specs.syncPaymentRequest('Eps', 'http://sb-gateway-internal.cko.lon/giropay-internal/eps/relations/gw/pay');
specs.syncPaymentResponse('Eps', 'http://sb-gateway-internal.cko.lon/giropay-internal/eps/relations/gw/payment');
specs.syncPaymentRequest('Ideal', 'http://sb-gateway-internal.cko.lon/ideal-internal-api/relations/gw/pay');
specs.syncPaymentResponse('Ideal', 'http://sb-gateway-internal.cko.lon/ideal-internal-api/relations/gw/payment');
specs.syncPaymentRequest('Klarna', 'http://sb-gateway-internal.cko.lon/klarna-internal/relations/gw/pay');
specs.syncPaymentResponse('Klarna', 'http://sb-gateway-internal.cko.lon/klarna-internal/relations/gw/payment');
specs.syncPaymentRequest('Knet', 'http://sb-gateway-internal.cko.lon/knet-internal/relations/gw/pay');
specs.syncPaymentResponse('Knet', 'http://sb-gateway-internal.cko.lon/knet-internal/relations/gw/payment');
specs.syncPaymentRequest('Bancontact', 'http://sb-gateway-internal.cko.lon/ppro-internal/bancontact/relations/gw/pay');
specs.syncPaymentResponse('Bancontact', 'http://sb-gateway-internal.cko.lon/ppro-internal/bancontact/relations/gw/payment');
specs.syncPaymentRequest('Fawry', 'http://sb-gateway-internal.cko.lon/fawry-internal/relations/gw/pay');
specs.syncPaymentResponse('Fawry', 'http://sb-gateway-internal.cko.lon/fawry-internal/relations/gw/payment');
specs.syncPaymentRequest('QPay', 'http://sb-gateway-internal.cko.lon/qpay-internal/relations/gw/pay');
specs.syncPaymentResponse('QPay', 'http://sb-gateway-internal.cko.lon/qpay-internal/relations/gw/payment');
specs.syncPaymentRequest('P24', 'http://sb-gateway-internal.cko.lon/ppro-internal/p24/relations/gw/pay');
specs.syncPaymentResponse('P24', 'http://sb-gateway-internal.cko.lon/ppro-internal/p24/relations/gw/payment');
specs.syncPaymentRequest('Multibanco', 'http://sb-gateway-internal.cko.lon/ppro-internal/multibanco/relations/gw/pay');
specs.syncPaymentResponse('Multibanco', 'http://sb-gateway-internal.cko.lon/ppro-internal/multibanco/relations/gw/payment');
specs.syncPaymentRequest('BenefitPay', 'http://sb-gateway-internal.cko.lon/benefitpay-internal/relations/gw/pay');
specs.syncPaymentResponse('BenefitPay', 'http://sb-gateway-internal.cko.lon/benefitpay-internal/relations/gw/payment');
specs.syncPaymentRequest('OXXO', 'http://sb-gateway-internal.cko.lon/dlocal-internal/oxxo/relations/gw/pay');
specs.syncPaymentResponse('OXXO', 'http://sb-gateway-internal.cko.lon/dlocal-internal/oxxo/relations/gw/payment');
specs.syncPaymentRequest('Boleto', 'http://sb-gateway-internal.cko.lon/dlocal-internal/boleto/relations/gw/pay');
specs.syncPaymentResponse('Boleto', 'http://sb-gateway-internal.cko.lon/dlocal-internal/boleto/relations/gw/payment');
specs.syncPaymentRequest('PagoFacil', 'http://sb-gateway-internal.cko.lon/dlocal-internal/pagofacil/relations/gw/pay');
specs.syncPaymentResponse('PagoFacil', 'http://sb-gateway-internal.cko.lon/dlocal-internal/pagofacil/relations/gw/payment');
specs.syncPaymentRequest('RapiPago', 'http://sb-gateway-internal.cko.lon/dlocal-internal/rapipago/relations/gw/pay');
specs.syncPaymentResponse('RapiPago', 'http://sb-gateway-internal.cko.lon/dlocal-internal/rapipago/relations/gw/payment');
specs.syncPaymentRequest('Baloto', 'http://sb-gateway-internal.cko.lon/dlocal-internal/baloto/relations/gw/pay');
specs.syncPaymentResponse('Baloto', 'http://sb-gateway-internal.cko.lon/dlocal-internal/baloto/relations/gw/payment');