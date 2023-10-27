# PetShop

## Pet Shop Application created 

The Pet Shop application receives lists of users and pets and writes maximum 10 Users and 20 Pets in PostgreSQL Database. After that buy command can be executed where each user
buys 1 pet if he has enough money and transaction log is created and  saved in the Database with the date of transaction, successful and unsuccessful transaction.

The application works with sending HTTP Get and Post requests to the following endpoints: <br>
    -<b> localhost:8080/pet </b> This is Post request for creating pets in the pet store <br>
    -<b> localhost:8080/user </b> This is Post request for creating users in the pet store <br>
    -<b> localhost:8080/user/buy </b> This is Get request for retrieving list of strings, that are sentences for which user bought which pet. <br>

There is Postman collection with HTTP requests attached to this repository for testing the application.

In order to run & use the application you need to connect to PostgreSQL database and provide your credentials in application.properties.


