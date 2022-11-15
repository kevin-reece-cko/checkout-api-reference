# Changelog

| Date       | Description of change                                                                                                                   |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| 2022/09/29 | Added new GET Payments endpoint                                                                                                         |                                                             
| 2022/09/21 | Added the `3ds.allow_upgrade` to payment requests & `3ds.upgrade_reason` to the 202 accepted & GET endpoint responses                   |
| 2022/09/12 | Amended paymentrequest/payout sender state field to have validation of <= 2 chars rather than <= 3 and fixed text description to match. |
| 2022/09/08 | Update PaymentRequest `processing` object to add `senderInformation` property                                                           |
| 2022/08/11 | Update Klarna Payment Request &  Response source.                                                                                       |
| 2022/08/03 | Add missing `token_format` to Google Pay and Apple Pay token responses.                                                                 |
| 2022/08/01 | Deprecated `baloto` payment method                                                                                                      |
| 2022/07/29 | Update Java, C#, PHP & Python code samples to match new SDK Version.                                                                    |
| 2022/07/14 | Add `locale` property to Get Payment Link details response                                                                              |
| 2022/05/11 | Added Arabic locale option to Hosted Payments Page and Payment Links.                                                                   |
| 2022/05/10 | Added `3ds.challenge_indicator` to Hosted Payments Page and Payment Links.                                                              |
| 2022/04/27 | Added Scandinavian locale options to Hosted Payments Page and Payment Links.                                                            |
| 2022/03/30 | Adds new locale options to Hosted Payments Page and Payment Links.                                                                      |
| 2022/03/28 | Update PHP code samples                                                                                                                 |
| 2022/03/08 | Change C# samples to suggest the usage of `await` instead of `.Result`                                                                  |
| 2022/02/23 | Added `allow_payment_methods` to Payment Links and Hosted Payments Page.                                                                |
| 2022/02/18 | Improved and added missing reporting code samples for Java & C#                                                                         |
| 2022/01/26 | Update code samples for Java.                                                                                                           |
| 2022/01/25 | Update code samples for C#.                                                                                                             |
| 2022/01/13 | Update code samples for Node JS.                                                                                                        |
| 2021/12/17 | Added `payment_type`, `payment_ip` and `billing_descriptor` to Hosted Payments and Payment Links Get endpoints.                         |
| 2021/12/14 | Removed upper limit for `products.price`                                                                                                |
| 2021/11/19 | Added specification for "Get Hosted Payments Page details" endpoint.                                                                    |
| 2021/11/11 | Added `3ds.challenge_indicator` to card payment requests.                                                                               |
| 2021/10/18 | Added `processing.purpose` to card payouts.                                                                                             |
| 2021/10/18 | Added `recommendation_code` to payment response.                                                                                        |
| 2022/11/09 | Removed the `risk` endpoints                                          |