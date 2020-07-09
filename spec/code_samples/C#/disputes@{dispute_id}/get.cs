var api = CheckoutApi.Create("your secret key");

var getDisputeResponse = await api.Disputes.GetDisputeAsync(id: "dsp_bc94ebda8d275i461229");