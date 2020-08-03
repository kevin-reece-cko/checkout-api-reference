var api = CheckoutApi.Create("your secret key");

var disputeEvidence = new DisputeEvidence()
{
    {"additional_evidence_file", "file_jmbfgkjromvcrn9t4qu4" },
    {"additional_evidence_text", "provide dispute evidence test" }
};

await api.Disputes.ProvideDisputeEvidenceAsync(
                    id: "dsp_bc94ebda8d275i461229",
                    disputeEvidence: disputeEvidence
                );