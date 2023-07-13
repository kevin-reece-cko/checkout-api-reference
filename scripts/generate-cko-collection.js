const fs = require('fs'),
	Converter = require('cko-openapi-to-postmanv2');
const openapiData = fs.readFileSync('web_deploy/swagger.yaml', { encoding: 'UTF8' });

const options = { folderStrategy: 'Tags', requestParametersResolution: 'Example' };

const userAgentPreRequest = [
	{
		listen: 'prerequest',
		script: {
			type: 'text/javascript',
			exec: ["pm.request.headers.add({key: 'User-Agent', value: 'CheckoutComPostman/1.0' })"],
		},
	},
	{
		listen: 'test',
		script: {
			type: 'text/javascript',
			exec: [''],
		},
	},
];

Converter.convert({ type: 'string', data: openapiData }, options, (err, conversionResult) => {
	if (!conversionResult.result) {
		console.log('Could not convert', conversionResult.reason);
	} else {
		conversionResult.output[0].data.event = userAgentPreRequest;
		const content = conversionResult.output[0].data;
		fs.writeFileSync('cko_collection_beta.json', JSON.stringify(content));
	}
});
