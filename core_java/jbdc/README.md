# Introduction

The jdbc project is a project that we have set and implemented in order to learn what is jdbc, how it works and how to interact with it, to be able to work efficiently with RDBMS, it allows us to work with CRUD operations.
We have used a docker container with PSQL technology to set the database.
Maven has been used to generate and structure the project.

# Test
To test the application we have run different commands to execute CRUD operations, and then we went to the database to verify that our operations were successful.

To create instanciate the database, we have used the `psql_docker.sh` script.

To create the database, we have used the `DatabaseConnectionManager` class.

To read, update and delete tables, we have used the `CustomerDAO` class. 


