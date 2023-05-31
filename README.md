# Checkout.com API reference

Checkout.com APIs accept and return JSON in the HTTP body, and return
standard [HTTP response codes](https://www.checkout.com/docs/resources/codes/http-response-codes).

Our API reference is written using the OpenAPI specification. We have one spec for
[NAS](https://api-reference.checkout.com/)
and one for
[ABC](https://api-reference.checkout.com/previous).
Merchants can download the specs using the download button on either version of the API reference.

The API reference is hosted on Vercel.

---

## Contributing

If you clone this repo to edit your spec, you will need to:

1. Install the dependencies using `npm i`
2. Run `npm run start` to view your changes on [http://localhost:3001](http://localhost:3001).

When you raise a PR, it will automatically tag
the [Docs Writers](https://github.com/orgs/cko-web/teams/docs-writers) team, of which the Tech Writing team are members.

A PR review from this team is required before any changes can be merged into `master`.
However, you'll still need approval from a relevant product manager before the changes are accepted. 

If you need help, ask in the #ask-docs Slack channel.

### Changelog
When making changes to the API reference, please add an entry to the relevant Changelog file (NAS or ABC).

**Note:** The Changelog.md file is public facing so ensure your Changelog entries are written with the public in mind and do not contain any internal Checkout.com product references or internal only details.

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

## Further reading
* [GitHub access and contributing to the APIRef](https://checkout.atlassian.net/wiki/spaces/PD/pages/5716771206/GitHub+access+and+contributing+for+the+APIRef)

