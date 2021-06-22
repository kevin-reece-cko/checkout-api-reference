var api = CheckoutApi.Create("your secret key");

var pathToFile = @"test_file.png";

var uploadFileResponse = await api.Files.UploadFileAsync(
                                          pathToFile: pathToFile,
                                          purpose: "dispute_evidence"
                                        );