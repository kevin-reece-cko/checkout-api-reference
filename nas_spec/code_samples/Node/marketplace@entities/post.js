import { Checkout } from 'checkout-node-sdk';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['marketplace'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	let marketplace = await cko.marketplace.onboardSubEntity({
		reference: 'superhero1234',
		contact_details: {
			phone: {
				number: '2345678910',
			},
		},
		profile: {
			urls: ['https://www.superheroexample.com'],
			mccs: ['0742'],
		},
		company: {
			business_registration_number: '452349600005',
			legal_name: 'Super Hero Masks Inc.',
			trading_name: 'Super Hero Masks',
			principal_address: {
				address_line1: '90 Tottenham Court Road',
				city: 'London',
				zip: 'W1T4TJ',
				country: 'GB',
			},
			registered_address: {
				address_line1: '90 Tottenham Court Road',
				city: 'London',
				zip: 'W1T4TJ',
				country: 'GB',
			},
			representatives: [
				{
					first_name: 'John',
					last_name: 'Doe',
					address: {
						address_line1: '90 Tottenham Court Road',
						city: 'London',
						zip: 'W1T4TJ',
						country: 'GB',
					},
					identification: {
						national_id_number: 'AB123456C',
					},
					phone: {
						number: '2345678910',
					},
					date_of_birth: {
						day: 5,
						month: 6,
						year: 1995,
					},
				},
			],
		},
	});
} catch (err) {
	console.log(err.name);
}
