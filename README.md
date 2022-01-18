# Checkout.com API reference

Checkout.com APIs accept and return JSON in the HTTP body, and return
standard [HTTP response codes](https://www.checkout.com/docs/resources/codes/http-response-codes).

Our API reference is written using the OpenAPI specification. We have one spec for
the [current account structure](https://api-reference.checkout.com/) and one for
the [new account structure](https://api-reference.checkout.com/preview/crusoe). Merchants can download the specs using
the download button on either version of the API reference.

The API reference is hosted on Vercel.

---

## Owners

The Tech Docs team own the API reference and work closely with product teams to produce guides on
the [main documentation](https://www.checkout.com/docs) site and the API reference.

- [@Ben Ahmady](https://github.com/ben-ahmady-cko)
- [@Chrisi Webster](https://github.com/chrisi-webster-cko)
- [@Cristina Szilagyi](https://github.com/cristina-szilagyi-cko)

---

## Contributing

Most teams write the spec before building out the functionality. The preferred way to contribute is using
our [GitHub workflow](https://checkout.atlassian.net/wiki/spaces/PD/pages/4844781738/GitHub+Actions+for+API+Ref), which
means you just have to edit the spec on your own GitHub organisation instead of editing directly on this one.

If you fork this repo to edit your spec, you will need to:

1. Install the dependencies using `npm i`
2. Run `npm run start` to view your changes on [http://localhost:3001](http://localhost:3001).

When you raise a PR, it will automatically tag
the [Docs Admin](https://github.com/orgs/checkout/teams/docs-admin/members) team for review.

---

### abc_spec or nas_spec?

Use the appropriate directory (or directories) for your change to the ABC or NAS specification(s). If you need any help,
please contact one of the [Owners](#owners).

---

### Running the project locally

To run this project locally, clone the repository and run these commands in sequence:

1. `npm i`
2. `npm run build:all`
    1. OR `npm run build:abc`
    2. OR `npm run build:nas`
3. `npm run start`

The project should then be available at port 3001 (navigate to `localhost:3001` in your browser).

For example:

`localhost:3001` (ABC)
`localhost:3001/preview/crusoe` (NAS)

---

### Previews

When you open a PR, Vercel will automatically create a preview link.
