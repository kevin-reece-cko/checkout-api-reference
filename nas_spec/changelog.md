# Changelog

| Date       | Description of change                                                                                                 |
| ---------- | --------------------------------------------------------------------------------------------------------------------- |
| 2022/05/11 | Added Arabic locale option to Hosted Payments Page and Payment Links.                                                 |
| 2022/05/10 | Added `3ds.challenge_indicator` to Hosted Payments Page and Payment Links.                                            |
| 2022/04/28 | Add recurring authentication type in Sessions.                                                                        |
| 2022/04/27 | Added Scandinavian locale options to Hosted Payments Page and Payment Links.                                          |
| 2022/04/27 | Added the `challenged` field to the GET payments response schema.                                                     |
| 2022/04/20 | Added required idempotency key to Transfers API                                                                       |
| 2022/04/19 | Update `3ds.exemption` available enums                                                                                |
| 2022/04/06 | Added `/payout-schedules` endpoint with `GET` and `PUT` methods to the Marketplace API                                |
| 2022/03/30 | Adds "Get action invocations" endpoint                                                                                           |
| 2022/03/28 | Update PHP code samples                                                                                               |
| 2022/03/25 | Increased max length for `reference` in "Onboard a sub-entity" to 50 characters |
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
