# Changelog

| Date       | Description of change                                                                                                 
|------------|-----------------------------------------------------------------------------------------------------------------------|
| 2022/01/04 | Add API key security to remaining Integrated Platforms endpoints.                                                     |
| 2023/01/03 | Add Platforms Update Payment Instrument Request spec.                                                                 |
| 2022/12/20 | Added `first_name` and `address` to `recipient`, deprecated `recipient.zip`, made `sender.address` optional.          |
| 2022/12/15 | Change Platforms schedule `by_day` response samples to use arrays.                                                    |
| 2022/12/15 | Add CV Connect NAS Request and Response source.                                                                       |
| 2022/12/15 | Add Trustly NAS Request and Response source.                                                                          |
| 2022/12/15 | Add Illicado NAS Request and Response source.                                                                         |
| 2022/12/14 | Add missing `available` property to Accounts Individual and Company responses                                         |
| 2022/12/13 | Remove third-party fields from integrated auth                                                                        |
| 2022/11/30 | Add API key security to IP endpoints.                                                                                 |
| 2022/11/29 | Adding prism device_session_id to payment request.                                                                    |
| 2022/11/15 | Adding a `entity` to BankPayoutRequest source.                                                                        |
| 2022/11/14 | Changing Card Metadata API request format                                                                             |
| 2022/11/09 | Removed the `risk` endpoints                                                                                          |
| 2022/11/03 | Added `score` property to the `risk` object on the 201 created payment response and GET details response              |
| 2022/11/02 | Fix indentation bug causing 'document' property to not be shown in `PlatformsPaymentInstrumentCreate.yaml`            |   
| 2022/11/02 | Ensure 'document' property is exposed on all relevant Platforms payment instruments schemas                           |
| 2022/11/02 | Add `default` property to Platforms payment instrument create examples                                                |
| 2022/11/02 | Fix indentation bug causing 'document' property to not be shown in `PlatformsPaymentInstrumentCreate.yaml`            |
| 2022/10/31 | Updated `payment_method` to be mandatory                                                                              |
| 2022/10/25 | Added Card Metadata API                                                                                               |
| 2022/10/20 | Removed wrong remark about app on Sessions channel data                                                               |
| 2022/10/20 | Added the `3ds.allow_upgrade` to payment requests & `3ds.upgrade_reason` to the 202 accepted & GET endpoint responses |
| 2022/10/18 | Add new challenge indicator for authentication: data_share                                                            |
| 2022/10/17 | Fixed Card and Token sources in Session to not have store_for_future_use                                              |
| 2022/10/11 | Add Tamara NAS Request and Response source.                                                                           |
| 2022/09/29 | Added new GET Payments endpoint                                                                                       |
| 2022/09/27 | Adding a `customer` to PaymentRequest as a source.                                                                    |
| 2022/09/27 | Split ProcessingSettings object into PaymentRequestProcessingSettings and CaptureRequestProcessingSettings            |
| 2022/09/22 | Add Integrated Platforms email address field and descriptions for French seller data                                  |
| 2022/09/16 | Corrected one of the Reports API paths.                                                                               |
| 2022/09/15 | Add Alipay Plus `processing.app_id` field to ProcessingData.                                                          |
| 2022/09/13 | Add scheme to session source.                                                                                         |
| 2022/09/12 | Amended address state field to have validation of <= 2 chars rather than <= 3 and fixed text description to match.    |
| 2022/09/06 | ADD missing challenge indicator field.                                                                                |
| 2022/09/06 | Added the `processing.partner_customer_id` field to ProcessingData.                                                   |
| 2022/09/05 | ADD Alma NAS Request source.                                                                                          |   
| 2022/09/02 | Adding `amount_allocations` object to Payment request, Capture and Payment Details                                    |
| 2022/08/29 | Add One Klarna Nas structure                                                                                          |
| 2022/08/26 | ADD KNET NAS Request and Response source                                                                              |
| 2022/08/25 | Add P24 NAS Request and Response source.                                                                              |
| 2022/08/29 | Add Postfinance, Bancontact and Multibanco NAS Request and Response sources                                           |
| 2022/08/26 | ADD KNET NAS Request and Response source                                                                              |
| 2022/08/25 | Add P24 NAS Request and Response source.                                                                              |
| 2022/08/23 | Add STC Pay                                                                                                           |
| 2022/08/19 | Add Benefit PG specific requirements to `reference` description                                                       |
| 2022/08/19 | Added alipay_plus type                                                                                                |
| 2022/08/17 | Added Reports API                                                                                                     |
| 2022/08/10 | Add Giropay, EPS Request and Response source.                                                                         |
| 2022/08/09 | Add Mbway NAS Request and Response source.                                                                            |
| 2022/08/09 | Add QPay Payment Request &  Response source.                                                                          |
| 2022/08/09 | Add Benefit PG Request and Response sources                                                                           |
| 2022/08/08 | Fixing document types for platforms                                                                                   |
| 2022/08/05 | Add AfterPay NAS Request and Response source.                                                                         |
| 2022/08/03 | Add discriminator for 3ds information                                                                                 |
| 2022/08/03 | Add missing `token_format` to Google Pay and Apple Pay token responses.                                               |
| 2022/07/29 | Adding `marketplace` object to capture and other minor fixes to IP space                                              |
| 2022/07/29 | Update Java, C#, PHP & Python code samples to match new SDK Version.                                                  |
| 2022/07/20 | Added Alipay Plus's e-wallets supports                                                                                |
| 2022/07/20 | Adding required fields for Platforms payment instruments and separate `corporate` and `individual` examples           |
| 2022/07/20 | Added `knet`, `giropay`, `bancontact`, `eps`, `p24`, and `multibanco` to Hosted Payments and Payment Links.           |
| 2022/07/19 | Updated example for Platforms payout schedules from `currency` to `GBP` and `ISO`                                     |
| 2022/07/19 | Update WeChat Pay NAS structure                                                                                       |
| 2022/07/14 | Add Sofort NAS Request and Response source.                                                                           |
| 2022/07/14 | Add `locale` property to Get Payment Link details response                                                            |
| 2022/07/14 | Added `customer`, `description`, `billing descriptor`, `shipping`, and `items` objects to Capture requests            |
| 2022/07/13 | Added fields for Level 2 and Level 3 data.                                                                            |
| 2022/07/13 | Added the `phone` object to `customer` object for payments.                                                           |
| 2022/07/13 | Adds fields required EU sellers using the Accounts API                                                                |
| 2022/07/13 | Added the `phone` object to `customer` object for payments.                                                           |
| 2022/07/11 | Replaced `identification` enum with `identity_verification` for Platform Files purpose                                |
| 2022/07/06 | Change `by_day` and `by_month_day` within Platform payout schedule to support multiple values.                        |
| 2022/07/04 | Rename instances of `instalment` to `installment` in Sessions.                                                        |
| 2022/05/28 | Add `instalment`, `add_card`, `maintain_card` authentication type in Sessions.                                        |
| 2022/06/27 | Added iDEAL NAS Request and Response Source                                                                           |
| 2022/05/23 | Update Alipay Plus NAS structure                                                                                      |
| 2022/06/01 | Marketplace API renamed to Accounts API                                                                               |
| 2022/05/23 | Added Alipay Plus NAS structure                                                                                       |
| 2022/05/19 | Added WeChat Pay NAS structure                                                                                        |
| 2022/05/18 | Added "Get transfer details"                                                                                          |
| 2022/05/11 | Added Arabic locale option to Hosted Payments Page and Payment Links.                                                 |
| 2022/05/10 | Added `3ds.challenge_indicator` to Hosted Payments Page and Payment Links.                                            |
| 2022/04/28 | Add recurring authentication type in Sessions.                                                                        |
| 2022/04/27 | Added Scandinavian locale options to Hosted Payments Page and Payment Links.                                          |
| 2022/04/27 | Added the `challenged` field to the GET payments response schema.                                                     |
| 2022/04/20 | Added required idempotency key to Transfers API                                                                       |
| 2022/04/19 | Update `3ds.exemption` available enums                                                                                |
| 2022/04/06 | Added `/payout-schedules` endpoint with `GET` and `PUT` methods to the Marketplace API                                |
| 2022/03/30 | Adds "Get action invocations" endpoint                                                                                |
| 2022/03/28 | Update PHP code samples                                                                                               |
| 2022/03/25 | Increased max length for `reference` in "Onboard a sub-entity" to 50 characters                                       |
| 2022/03/22 | Added new scheme `cartes_bancaires` to enum `scheme` in Get and Create Sessions Responses                             |
| 2022/03/22 | Fixed invalid format for `authentication_date`                                                                        |
| 2022/03/18 | Added Cartes Bancaires changes to Sessions request and response.                                                      |
| 2022/03/16 | Adds `document` object to the `company` object in the Marketplace API                                                 |
| 2022/03/09 | Added the `provider_token` payment request source type.                                                               |
| 2022/03/08 | Change C# samples to suggest the usage of `await` instead of `.Result`                                                |
| 2022/03/02 | Adds Transfers and Balances                                                                                           |
| 2022/02/23 | Adds Hosted Payments Page and Payment Links                                                                           |
| 2022/02/18 | Added `Increment Payment Authorization` code samples for Java & C#                                                    |
| 2022/02/02 | Adds `active` property for workflows                                                                                  |
| 2022/01/26 | Update code samples for Java.                                                                                         |
| 2022/01/25 | Update code samples for C#.                                                                                           |
| 2022/01/19 | Added test a workflow endpoint.                                                                                       |
| 2022/01/13 | Update code samples for Node JS.                                                                                      |
| 2021/11/29 | Increase max length of the NAS `success_url` and `failure_url` fields of the payment request (both from 255 to 1024). |
| 2021/11/11 | Added `3ds.challenge_indicator` to card payment requests.                                                             |
| 2021/11/03 | Adds `identification` object under parent `sender` object in payment request.                                         |
| 2021/10/18 | Added the `marketplaces.sub-entities` object to support split payments.                                               |
