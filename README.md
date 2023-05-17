# Checkout.com API reference

Checkout.com APIs accept and return JSON in the HTTP body, and return
standard [HTTP response codes](https://www.checkout.com/docs/resources/codes/http-response-codes).

Our API reference is written using the OpenAPI specification. We have one spec for
the [current account structure](https://api-reference.checkout.com/) and one for
the [new account structure](https://api-reference.checkout.com/preview/crusoe). Merchants can download the specs using
the download button on either version of the API reference.

The API reference is hosted on Vercel.

## ABC

Append `/previous` to all links (including preview ones) for the ABC API reference.

---

## Contributing

Most teams write the spec before building out the functionality. The preferred way to contribute is using
our [GitHub workflow](https://checkout.atlassian.net/wiki/spaces/PD/pages/4844781738/GitHub+Actions+for+API+Ref), which
means you just have to edit the spec on your own GitHub organisation instead of editing directly on this one.

If you fork this repo to edit your spec, you will need to:

1. Install the dependencies using `npm i`
2. Run `npm run start` to view your changes on [http://localhost:3001](http://localhost:3001).

When you raise a PR, it will automatically tag
the [Docs Writers](https://github.com/orgs/cko-web/teams/docs-writers) team, of which the Tech Writing team are members.

A PR review from this team is required before any changes can be merged into `master`. However, you'll still need approval from a relevant product manager before the changes are accepted. 

If you need help, ask in the #ask-docs Slack channel.

<br>

### abc_spec or nas_spec?

Use the appropriate directory (or directories) for your change to the ABC or NAS specification(s).

---

## Running the project locally

To run this project locally, clone the repository and run these commands in sequence:

1. `npm i`
2. `npm run build:all`
    1. OR `npm run build:abc`
    2. OR `npm run build:nas`
3. `npm run start`

The project should then be available at port 3001 (navigate to `localhost:3001` in your browser), for example:

- `localhost:3001/previous` (ABC)
- `localhost:3001` (NAS)
