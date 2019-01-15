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
  syncPaymentRequest('Klarna', 'http://qa-gateway-internal.cko.lon/klarna-internal/relations/gw/pay', '14');
  syncPaymentResponse('Klarna', 'http://qa-gateway-internal.cko.lon/klarna-internal/relations/gw/pay', '10');
}

var syncPaymentRequest = function(paymentSourceName, paymentSpecUrl, outputFilePrefix) {
  var outputFilename =  outputFilePrefix + '_PaymentRequest' + paymentSourceName + 'Source.yaml'
  var outputFilePath = 'spec/components/schemas/Payments/RequestSources/' + outputFilename;
  var buildPaymentRequestFunction = getFunctionToBuildPaymentRequest(paymentSourceName);
  sync(paymentSpecUrl, buildPaymentRequestFunction, outputFilePath);
};

var syncPaymentResponse = function(paymentSourceName, paymentSpecUrl, outputFilePrefix) {
  var outputFilename =  outputFilePrefix + '_PaymentResponse' + paymentSourceName + 'Source.yaml'
  var outputFilePath = 'spec/components/schemas/Payments/ResponseSources/' + outputFilename;
  var buildPaymentResponseFunction = getFunctionToBuildPaymentResponse(paymentSourceName);
  sync(paymentSpecUrl, buildPaymentResponseFunction, outputFilePath);
};

var sync = function(paymentSpecUrl, buildPaymentResponseFunction, outputFilePath) {
  console.log('Requesting payment spec from: ' + paymentSpecUrl + ', to output: ' + outputFilePath);
  var buildOutputFunction = getFunctionToBuildYaml(buildPaymentResponseFunction);
  request({ url: paymentSpecUrl, json: true}, thenBuildOutputAndWrite(buildOutputFunction, outputFilePath));
};

var getFunctionToBuildPaymentRequest = function(paymentSourceName) {
  return function(responseBody) {
    var requestData = responseBody.put.requestBody.content['application/json'].schema.properties.request_data;
    if (requestData.properties.type) {
      delete requestData.properties.type;
    }
    addDescriptionToKlarnaPassthroughObjects(requestData, paymentSourceName);
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
};

var addDescriptionToKlarnaPassthroughObjects = function(requestData, paymentSourceName) {
  Object.keys(requestData.properties).forEach(propertyName => {
    var property = requestData.properties[propertyName];
    if (property['x-cko-passthrough'] === true && property['x-klarna-docs']) {
      var apmPropertyName = property['x-klarna-name'] || propertyName;
      var apmPropertyDocs = property['x-klarna-docs'];
      property.description += '  \nThis object is passed directly to ' + paymentSourceName + ' as `' + apmPropertyName + '`, \
                                so for the object definition use the [' + paymentSourceName + ' documentation](' + apmPropertyDocs + ').';
    }
  });
};

var getFunctionToBuildPaymentResponse = function(paymentSourceName) {
  return function(responseBody) {
    var requestData = responseBody.put.responses['201'].content['application/json'].schema.properties.response_data;
    if (requestData.properties.type) {
      delete requestData.properties.type;
    }
    return {
      type: 'object',
      description: paymentSourceName + ' Source',
      allOf: [{
          $ref: '#/components/schemas/PaymentResponseSource',
        }, {
          type: 'object',
          properties: requestData.properties
        }
      ]
    };
  };
};

var getFunctionToBuildYaml = function(buildOutputFunction) {
  return function(responseBody) {
    var builtOutput = buildOutputFunction(responseBody);
    var generatedFileWarningComment = '###\n# Warning: this file was auto generated from OpenAPI specs using \'npm run sync-generated-specs\'. Do not manually edit. \n###\n';
    return generatedFileWarningComment + YAML.stringify(builtOutput, 20, 2);
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