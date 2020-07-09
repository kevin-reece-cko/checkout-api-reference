var api = CheckoutApi.Create("your secret key");

var sourceRequest = new SourceRequest
(
  "sepa",
  new Address()
  {
    AddressLine1 = "Checkout.com",
    AddressLine2 = "Shepherdess Walk",
    City = "London",
    State = "London",
    Zip = "N1 7LH",
    Country = "GB"
  }
)
{
  Reference = "X-080957-N34",
  Phone = new Phone()
  {
    CountryCode = "+1",
    Number = "415 555 2671"
  },
  SourceData = new SourceData()
  {
    { "first_name", "Marcus" },
    { "last_name", "Barrilius Maximus" },
    { "account_iban", "DE68100100101234567895" },
    { "bic", "PBNKDEFFXXX" },
    { "billing_descriptor", "Test" },
    { "mandate_type", "single" }
  }
};

try
{
  var sourceResponse = await api.Sources.RequestAsync(sourceRequest);
  var source = sourceResponse.Source;
}
catch (CheckoutValidationException validationEx)
{
  return ValidationError(validationEx.Error);
}
catch (CheckoutApiException apiEx)
{
  Log.Error("Source request failed with status code {HttpStatusCode}", apiEx.HttpStatusCode);
  throw;
}