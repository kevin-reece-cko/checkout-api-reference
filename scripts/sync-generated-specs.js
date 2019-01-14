#!/usr/bin/env node
'use strict';
var request = require('request');
var YAML = require('yamljs');
var fs = require('fs');
require('shelljs/global');
set('-e');
set('-v');

var syncPaymentSources = function()
{
  syncPaymentSource('Klarna', 'http://qa-gateway-internal.cko.lon/klarna-internal/relations/gw/pay', '14');
}

var syncPaymentSource = function(paymentSourceName, paymentSpecUrl, outputFilePrefix) {
  console.log('Requesting ' + paymentSourceName + ' payment spec from: ' + paymentSpecUrl);
  var outputFilename =  outputFilePrefix + '_PaymentRequest' + paymentSourceName + 'Source.yaml'
  var outputFilePath = 'spec/components/schemas/Payments/RequestSources/' + outputFilename;
  var buildOutputFunction = getFunctionToBuildPaymentSourceYaml(paymentSourceName);
  request({ url: paymentSpecUrl, json: true}, thenBuildOutputAndWrite(buildOutputFunction, outputFilePath));
};

var getFunctionToBuildPaymentSourceYaml = function(paymentSourceName) {
  return function(responseBody) {
    var requestData = responseBody.put.requestBody.content['application/json'].schema.properties.request_data;
    var builtPaymentSource = buildPaymentSource(requestData, paymentSourceName);
    var generatedFileWarningComment = '###\n# Warning: this file was auto generated from OpenAPI specs using \'npm run sync-generated-specs\'. Do not manually edit. \n###\n';
    return generatedFileWarningComment + YAML.stringify(builtPaymentSource, 20, 2);
  };
};

var buildPaymentSource = function(requestData, paymentSourceName) {
  if (requestData.properties.type) {
    delete requestData.properties.type;
  }
  return {
    type: 'object',
    description: paymentSourceName + ' Source',
    allOf: [{
        $ref: '#/components/schemas/PaymentRequestSource',
      }, {
        type: 'object',
        required: requestData.required,
        properties: requestData.properties
      }
    ]
  };
};

var thenBuildOutputAndWrite = function(buildOutputFunction, outputFilePath) {
  return function(error, response, responseBody) {
    if (error || response.statusCode !== 200) {
      handleRequestError(error, response);
    } else {
      console.log('Response from url received.');
      var builtOutput = buildOutputFunction(responseBody);
      fs.writeFile(outputFilePath, builtOutput, thenHandleFileWrite(outputFilePath));
    }
  }
};

var handleRequestError = function(error, response) {
  console.log('Invalid response from url. Error = ' + error);
  if (response) {
    console.log('response.statusCode = ' + response.statusCode);
  }
};

var thenHandleFileWrite = function(outputFilePath) {
  return function (error) {
    if (error) {
      console.log('File write error received: ' + error);
    } else {
      console.log('File written to ' + outputFilePath);
    }
  }
}; 

syncPaymentSources();