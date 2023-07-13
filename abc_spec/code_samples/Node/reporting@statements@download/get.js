import { Checkout } from 'checkout-sdk-node';

const cko = new Checkout('sk_XXXX');

try {
	const statement = await cko.reconciliation.getStatementCsv('155613B100981');
} catch (err) {
	console.log(err.name);
}
