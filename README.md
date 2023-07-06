# Checkout.com API reference

Our ABC and NAS API references are intended as comprehensive guides to Checkout.com's ABC and NAS REST APIs, respectively. They are available publicly from our API reference sites, and can be downloaded by merchants.  

The [NAS API reference](https://api-reference.checkout.com/) is built from the specifications defined within the `nas_spec` folder. The [ABC API reference](https://api-reference.checkout.com/previous) is built from the specifications defined within the `abc_spec` folder.

Both specifications are written in accordance with the [OpenAPI specification](https://swagger.io/specification/).

As the API reference specifications are maintained separately to the various APIs (Unified Payments, Risk, Reports, etc), we rely on our product peers to update the API reference specifications when they add, update, or deprecate functionality.

---

## Tooling

We use [Redocly](https://redocly.com/) to generate the API reference sites from the specifications in the repository, and [Vercel](https://vercel.com/) to deploy the sites.

---

## Release cycle

The API reference is released weekly, on Wednesdays. This mirrors the release of our documentation sites.

---

## Contribute to the API reference

To contribute to the API reference, you must first be added to the correct GitHub org and team: 

1. [Submit a ticket](https://checkoutsupport.freshservice.com/support/catalog/items/52) for GitHub access on FreshService.
2. In the _Organisation_ dropdown, select _Existing Organisations_.
3. In the _GitHub Organisation_ dropdown, select _cko-web_.
4. In the _Additional notes_ field, request to be added to the `API Ref Collaborators` team in the `CKO Web` org.


Once you've been added:

1. Clone the repository.
2. Create a new feature branch that branches off of `staging`.
3. Edit the API spec in your feature branch. The spec for the NAS API is found within `nas_spec`. The spec for the ABC API is found within `abc_spec`.
4. Add a new entry documenting your change to the `nas_spec/changelog.md` or `abc_spec/changelog.md` file, depending on which spec you updated. The changelog entries are public-facing – do not include references to any private or internal details, or internal Checkout.com products or implementations. 
5. Raise a pull request (PR) against `staging`.
6. Request a documentation review from the tech writers in the #ask-docs Slack channel. 

A tech writer will pick up your PR and leave comments for documentation updates throughout your PR.

Once you've applied the changes, the tech writer will approve your PR. You may also need to request approval if you have touched a file with a defined [code owner](https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-code-owners).

When you have the required approvals, merge your PR using the _Squash and Merge_ option.

When your change is in `staging`, it will be included in the next API reference release.

For more information, see [here](https://checkout.atlassian.net/wiki/spaces/PD/pages/5716771206/GitHub+access+and+contributing+for+the+APIRef).

---

## Preview changes locally

Vercel preview sites are no longer generated for new API reference PRs. However, you can still run the project locally to preview your changes:

1. From your command-line, `cd` into the directory where you cloned the project.
2. Run `npm i` to install the required dependencies.
3. Run `npm run build:nas` to build the NAS API reference site, or `npm run build:abc` to build the ABC API reference site. To build both, run `npm run build:all`
4. Run `npm run start` to run the project locally.

The project will be available at port `3001`. Use the following URLs to access the preview sites:

- `localhost:3001`, for the NAS API reference site
- `localhost:3001/previous`, for the ABC API reference site

You can find more detailed steps [here](https://checkout.atlassian.net/wiki/spaces/PD/pages/5352161454/Building+the+CKO+API+reference+locally).

---

## Code samples

The code samples used across the API reference are written and maintained by our partner agency, Parser.

NAS API reference samples are located within the `nas_spec/code_samples` directory. ABC API reference samples are located within the `abc_spec/code_samples` directory.


