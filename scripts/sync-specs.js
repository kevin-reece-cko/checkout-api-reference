#!/usr/bin/env node
'use strict';
var request = require('request');
var YAML = require('yamljs');
var fs = require('fs');
require('shelljs/global');
set('-e');
set('-v');

var syncPaymentRequest = function(paymentSourceName, paymentSpecUrl) {
  var outputFilename =  'PaymentRequest' + paymentSourceName + 'Source.yaml'
  var outputFilePath = 'spec/components/schemas/Payments/RequestSources/' + outputFilename;
  var buildPaymentRequestFunction = getFunctionToBuildPaymentRequest(paymentSourceName);
  sync(paymentSpecUrl, buildPaymentRequestFunction, outputFilePath);
};

var syncPaymentResponse = function(paymentSourceName, paymentSpecUrl) {
  var outputFilename =  'PaymentResponse' + paymentSourceName + 'Source.yaml'
  var outputFilePath = 'spec/components/schemas/Payments/ResponseSources/' + outputFilename;
  var buildPaymentResponseFunction = getFunctionToBuildPaymentResponse(paymentSourceName);
  sync(paymentSpecUrl, buildPaymentResponseFunction, outputFilePath);
};

var sync = function(paymentSpecUrl, buildPaymentResponseFunction, outputFilePath) {
  console.log('Requesting payment spec from: ' + paymentSpecUrl + ', to output: ' + outputFilePath);
  var buildOutputFunction = getFunctionToBuildYaml(buildPaymentResponseFunction);
  request({ url: paymentSpecUrl, json: true}, thenBuildOutputAndWrite(buildOutputFunction, outputFilePath));
};

// Copy properties that should be included in the merchant-facing API specification
var copyRelevantProperties = function(outputSchema, inputSchema) {
  if (!inputSchema)
    return;

  outputSchema.type = 'object';

  if (inputSchema.required) {
    outputSchema.required = inputSchema.required;
  }
  
  if (inputSchema.properties) {
    outputSchema.properties = inputSchema.properties;
    if (outputSchema.properties.type) {
      delete outputSchema.properties.type;
    }
  }

  if (inputSchema.allOf) {
    outputSchema.allOf = inputSchema.allOf;
    if (outputSchema.allOf.properties && outputSchema.allOf.properties.type) {
      delete outputSchema.allOf.properties.type;
    }
  }

  if (inputSchema.anyOf) {
    outputSchema.anyOf = inputSchema.anyOf;
    if (outputSchema.anyOf.properties && outputSchema.anyOf.properties.type) {
      delete outputSchema.anyOf.properties.type;
    }
  }

  if (inputSchema.oneOf) {
    outputSchema.oneOf = properties.response_data.oneOf;
    if (outputSchema.oneOf.properties && outputSchema.oneOf.properties.type) {
      delete outputSchema.oneOf.properties.type;
    }
  }

  return outputSchema;
}

var getFunctionToBuildPaymentRequest = function(paymentSourceName) {
  return function(responseBody) {
    var properties = responseBody.put.requestBody.content['application/json'].schema.properties;
    var requestData = {};
    if (properties) {
      copyRelevantProperties(requestData, properties.request_data);
    }
    addDescriptionToKlarnaPassthroughObjects(requestData, paymentSourceName);
    return {
      type: 'object',
      description: paymentSourceName + ' Source',
      allOf: [{
          $ref: '#/components/schemas/PaymentRequestSource',
        }, 
        requestData
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
      property.description += '  \nThis object is passed directly to ' + paymentSourceName + ' as `' + apmPropertyName + '`, ' +
                                '\nso for the object definition use the [' + paymentSourceName + ' documentation](' + apmPropertyDocs + ').';
    }
  });
};

var getFunctionToBuildPaymentResponse = function(paymentSourceName) {
  return function(responseBody) {
    var properties = responseBody.get.responses[200].content['application/json'].schema.properties;
    var responseData = {};
    if (properties) {
      copyRelevantProperties(responseData, properties.response_data);
    }
    return {
      type: 'object',
      description: paymentSourceName + ' Source',
      allOf: [{
          $ref: '#/components/schemas/PaymentResponseSource',
        }, 
        responseData
      ]
    };
  };
};

var getFunctionToBuildYaml = function(buildOutputFunction) {
  return function(responseBody) {
    var builtOutput = buildOutputFunction(responseBody);
    var generatedFileWarningComment = '###\n# Warning: this file was auto generated from OpenAPI specs. Do not manually edit. \n###\n';
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

exports.syncPaymentRequest = syncPaymentRequest;
exports.syncPaymentResponse = syncPaymentResponse;
