WalletTokenRequest walletTokenRequest = new WalletTokenRequest('applepay', tokenData);
// infered type: "applepay"
try {
    TokenResponse tokenRequest = api.tokensClient().requestAsync(walletTokenRequest).get();
    String token  = tokenRequest.getToken();
    return token;
} catch (CheckoutValidationException e) {
    return validationError(e.getError());
} catch (CheckoutApiException e) {
    LOG.severe("Payment request failed with status code " + e.getHttpStatusCode());
    throw e;
}
