var api = CheckoutApi.Create("your secret key");

var getDisputeEvidenceResponse = await api.Disputes.GetDisputeEvidenceAsync(id: "dsp_bc94ebda8d275i461229");