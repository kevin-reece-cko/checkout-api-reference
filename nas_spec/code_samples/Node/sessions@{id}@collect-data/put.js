import { Checkout } from 'checkout-node-sdk';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['sessions:browser'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	let session = await cko.sessions.update('sid_rwhwl4kb3eeenglibbvej2qtdy', {
		channel: 'browser',
		accept_header: 'Accept:  *.*, q=0.1',
		java_enabled: true,
		language: 'FR-fr',
		color_depth: '16',
		screen_height: '1080',
		screen_width: '1920',
		timezone: '60',
		user_agent:
			'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36',
		ip_address: '1.12.123.255',
	});
} catch (err) {
	console.log(err.name);
}
