# Sylvan Library API

The REST API for the Sylvan Library project

## Developer Setup

### DB Setup

In order to do local development, you'll need to create a local Postgres database. The project is set up to migrate the
database for you; you just need to create a local DB with the proper name and users.

#### Install Postgres

`brew install postgres`

#### User creation

First, create a user for use with the API:

`createuser -d -P sylvanlibrary`

At the password prompt, enter `jacesucks`.

#### DB Creation

Now, you should create a database _as the user you just created_:

`createdb -U sylvanlibrary sylvanapi`

#### Test the connection

`./gradlew flywayInfo`

#### Migrate the database

`./gradlew flywayMigrate`

## Running the Project

### From the command line

`./gradlew run`

### From IntelliJ

[TBD]

## Running tests

### From the command line

`./gradlew test`

### From IntelliJ

[TBD]
