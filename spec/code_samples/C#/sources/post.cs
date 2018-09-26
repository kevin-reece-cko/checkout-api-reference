var sourceRequest = new AddSourceRequest(SourceTypes.Sepa)
{
    Reference = "X-080957-N34",
    BillingAddress = new Address { ... },
    Phone = new Phone { ... },
    CustomerId = "cus_y3oqhf46pyzuxjbcn2giaqnb44"
}

sourceRequest.SourceData.Add("iban", "DE89 3704 0044 0532 0130 00");

var response = await api.Sources.AddAsync(sourceRequest);