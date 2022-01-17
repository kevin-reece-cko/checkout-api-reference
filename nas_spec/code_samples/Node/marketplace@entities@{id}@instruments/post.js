import { Checkout } from 'checkout-node-sdk';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['marketplace'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	let marketplace = await cko.marketplace.addPaymentInstrument('ent_aneh5mtyobxzazriwuevngrz6y', {
		label: "Peter's Personal Account",
		type: 'bank_account',
		account_number: '12345678',
		bank_code: '050389',
		currency: 'GBP',
		country: 'GB',
		account_holder: {
			first_name: 'Peter',
			last_name: 'Parker',
			billing_address: {
				address_line1: '90 Tottenham Court Road',
				city: 'London',
				state: 'London',
				zip: 'W1T 4TJ',
				country: 'GB',
			},
		},
		document: {
			type: 'bank_statement',
			file_id: 'file_wxglze3wwywujg4nna5fb7ldli',
		},
	});
} catch (err) {
	console.log(err.name);
}
