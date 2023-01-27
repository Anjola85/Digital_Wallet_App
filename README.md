# Running the application
- Pre-requisite: docker, postgreSQL, intelliJ, postman
1. Paste this in the terminal `make dev` to build the docker container
2. Run the application in intelliJ
3. Connect and run http requests on localhost//:5100

## Setting up the Database
* Run the following in the terminal;
  1. `docker ps`: to list the running containers 
  2. `docker exec -it {name_of_container} bash`: ability to execute shell commands within the terminal
  3. `psql -U admin -d quicksend`
  4. `\l`: to list the database, you should see the created database **quicksend**
  5. `\d`: to list the tables in the database
  6. `'SELECT * from users'`: list all users in the database