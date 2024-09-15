# Development

## Table of contents

* [Requirements](#requirements)
* [Test](#test)
* [Creating the Changelog](#creating-the-changelog)
* [Publish artifacts](#publish-artifacts)
    * [Publish to Sona Type (Maven central)](#publish-to-sona-type-maven-central)
    * [Publish to Maven local](#publish-to-maven-local)

## Requirements

* OpenJDK 21
* Deno - [Install Deno](https://deno.com/)
* Git cliff - [Install git cliff](./install-git-cliff.md)
* Make
* Gradle 8.5 (wrapper is recommended)

## Test

For test the project you should run this command

```sh
$ ./gradlew cleanTest check
```

## Creating the Changelog

Before creating the `CHANGELOG.md` you should ensure you are using [conventional commits](https://www.conventionalcommits.org/en/v1.0.0/).

You should create the tags following [semantic versioning](https://semver.org/).

Create the first version tag.

```sh
$ make bump-first-version
```

When you have new commits for the new version you should calculate the new version before create the tag.

Calculate the new version and create the tag.
```sh
$ make bump-version
```

Also, you can generate the changelog only for the current version.
```sh
$ git cliff --current
```

## Publish artifacts

⚠️ **CAUTION** ⚠️

**The artifacts should be published only by the owner of the repository.**

Before publish you need to create the `gradle.properties` file on the `.gradle` directory on your home directory
and create the GPG keys.

Create the GPG keys.
```sh
$ gpg --gen-key
```

Get the GPG key id.
```sh
$ gpg -K
```

Upload the keys to the ubuntu keyserver.
```sh
$ gpg --keyserver keyserver.ubuntu.com --send-keys <your gpg key id>
```

Export the secret keys to a keyring on your home directory.
```sh
$ gpg --keyring secring.gpg --export-secret-keys > ~/.gnupg/secring.gpg
```

Edit or create the `gradle.properties` file with your favorite text editor.
```sh
$ vim ~/.gradle/gradle.properties
```

And put the following content at the end of the file.
```text
signing.keyId=<the last 8 digits of the key id>
signing.password=<your gpg key passphrase>
signing.secretKeyRingFile=<absolute path to your home directory>/.gnupg/secring.gpg

mavenCentralUsername=<sonatype user token>
mavenCentralPassword=<sonatype password token>
```

Or you can setup them exposing the following environment variables
```sh
export ORG_GRADLE_PROJECT_signingInMemoryKeyId=<the last 8 digits of the key id>
export ORG_GRADLE_PROJECT_signingInMemoryKeyPassword=<your gpg key passphrase>
export ORG_GRADLE_PROJECT_signingInMemoryKey=<exported_ascii_armored_key>

export ORG_GRADLE_PROJECT_mavenCentralUsername=<sonatype user token>
export ORG_GRADLE_PROJECT_mavenCentralPassword=<sonatype password token>
```

If you want to publish a gradle plugin you need to append the following content.
You may find your keys on the user page of the [Gradle Plugin Portal](https://plugins.gradle.org/)
```text
gradle.publish.key=<your publish key>
gradle.publish.secret=<your publish secret>
```

### Publish to Sona Type (Maven central)

```sh
$ ./gradlew publishToCentralPortal
```

### Publish to Maven local

```sh
$ ./gradlew publishToMavenLocal
```
