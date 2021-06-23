var api = CheckoutApi.Create("your secret key", useSandbox: true, "your public key");
var tokenData = new Dictionary<string, object>
{
  { "version", "EC_v1" },
  { "data", "t7GeajLB9skXB6QSWfEpPA4WPhD..." },
  { "signature", "MIAGCSqGSIb3DQEHAqCAMI..." },
  { "header", new Dictionary<string, string>
    {
      { "ephemeralPublicKey", "MFkwEwYHK..." },
      { "publicKeyHash", "tqYV+tmG9aMh+l..." },
      { "transactionId", "3cee89679130a4..." }
    }
  }
};
var request = new WalletTokenRequest(WalletType.ApplePay, tokenData);

try
{
  var response = await api.Tokens.RequestAsync(request);
  var token = response.Token;
}
catch (CheckoutValidationException validationEx)
{
  return ValidationError(validationEx.Error);
}
catch (CheckoutApiException apiEx)
{
  Log.Error("Token request failed with status code {HttpStatusCode}", apiEx.HttpStatusCode);
  throw;
}
