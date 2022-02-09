# Spelling Correction Batch Demo
* This is a simple demonstration of a JSR 352 Batch job that reads from a database, corrects spelling errors, and then updates the database table.
* This is a good overview from Oracle - https://www.oracle.com/technical-resources/articles/java/batch-processing-ee-7.html
* I also prepared this presentation for the San Antonio Java Users Group in 2020 - https://www.slideshare.net/secret/sDOQuVlBZWyRrB

## Structure of job
* In `CustomJobListener`, a `DataHandler` is responsible for deleting data from the database tables and then inserting data.
* The `BATCH_JOB` table stores _metadata_ about each job execution. `ACCOUNT` is the main database table containing customer records.
* The `JdbcReader` queries from `ACCOUNT`. `CorrectStatusTypoProcessor` handles updating the status. Finally, `JdbcWriter` updates the remediated record.
* The _after_ method updates the _metadata_ in the `BATCH_JOB` table.
* **NOTE:** SQLite is used for the database. The `batch.db` file in the main project contains the database with 2 tables. SQLite is an excellent, lightweight, database.

### Running this locally
* The command to run this job is `gradlew clean build run` (or `/gradlew` if running in a terminal outside of IntelliJ).
