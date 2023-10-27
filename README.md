# PetShop

## Pet Shop Application created 

The Pet Shop application receives lists of users and pets and writes a maximum of 10 Users and 20 Pets in the PostgreSQL Database. After that, a buy command can be executed where each user buys one pet if they have enough money, and a transaction log is created and saved in the database with the date of the transaction, successful and unsuccessful transactions.

The application works by sending HTTP Get and Post requests to the following endpoints:
    - localhost:8080/pet: This is a Post request for creating pets in the pet store.
    - localhost:8080/user: This is a Post request for creating users in the pet store.
    - localhost:8080/user/buy: This is a Get request for retrieving a list of strings, which are sentences describing which user bought which pet.
    
There is a Postman collection with HTTP requests attached to this repository for testing the application.

In order to run and use the application, you need to connect to the PostgreSQL database and provide your credentials in the application.properties file.


