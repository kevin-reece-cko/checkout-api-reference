#!/usr/bin/env node
'use strict';
var Path = require('path');

require('shelljs/global');
set('-e');

mkdir('-p', 'web_deploy/previous');

cp('-R', 'web/*', 'web_deploy/');

exec('dotnet build src/OpenApiGenerator/OpenApiGenerator.csproj');
exec(`cross-env ASPNETCORE_ENVIRONMENT=${process.env.ACCOUNT} dotnet run -p src/OpenApiGenerator/OpenApiGenerator.csproj`);

if (process.env.ACCOUNT === 'nas') {
	cp('-R', 'output/*', 'web_deploy/');
} else {
	cp('-R', 'output/*', 'web_deploy/previous');
}
rm('-rf', 'output');

var SWAGGER_UI_DIST = Path.dirname(require.resolve('swagger-ui'));
rm('-rf', 'web_deploy/swagger-ui/');
cp('-R', SWAGGER_UI_DIST, 'web_deploy/swagger-ui/');

if (process.env.ACCOUNT === 'nas') {
	sed(
		'-i',
		'http://petstore.swagger.io/v2/swagger.json',
		'../swagger.json',
		'web_deploy/index.html'
	);
} else {
	sed('-i', 'http://petstore.swagger.io/v2/swagger.json', '../previous/swagger.json', 'web_deploy/previous/index.html');
}
