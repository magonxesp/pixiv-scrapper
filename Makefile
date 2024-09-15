.PHONY: first-version bump-version bump-version-alpha bump-version-beta

bump-first-version:
	@deno run --allow-run --allow-write --allow-read scripts/bump-version.js --first-version

bump-version:
	@deno run --allow-run --allow-write --allow-read scripts/bump-version.js

bump-version-alpha:
	@deno run --allow-run --allow-write --allow-read scripts/bump-version.js --alpha

bump-version-beta:
	@deno run --allow-run --allow-write --allow-read scripts/bump-version.js --beta
