# Peerless Assessment
## Olutobi Ogunsola
- A repository containing code for a simple transfer scheduling service in Spring Boot

## Program Architecture
- The program is written in a simple MVC pattern with the controller as the entry point into the program.
- The controller receives the request and calls the transactions service to invoke the appropriate method to handle the request.
- The transactions service interacts with a repository extending the jpa repository.
- This repository provides us with the CRUD functionality used in the program.
- H2 Database is configured and used in the program and can be managed using the ```http://localhost:8080/h2-console``` console.

## How To Run
1. Create a folder to contain the project
2. Clone this repository into the folder using ```git clone https://github.com/olutobiogunsola/peerless_assessment```
3. Run ```mvn install``` to install the project dependencies
4. Run the program. It should start up a tomcat server on ```http://localhost:8080```

The program contains the following functions
- Create a transaction
- Retrieve all transactions
- Retrieve all transactions by a sender id
- Cancel a transaction

## Create a transaction

Endpoint - ```POST http://localhost:8080/transactions/```
Payload - ```{
    "senderAccount": "olutobiogunsola",
    "recipientAccount": "peerless_sgs",
    "amount": 100,
    "transferDate": "2026-02-12"
}```
> [!IMPORTANT]
> The program will reject any amount below 0 with an appropriate error response, and will reject any date in the past as well.

The program will return a success response and return the saved transaction in the response object.

## Retrieve all transactions
Endpoint - ```GET http://localhost:8080/transactions/```
- Retrieves all transactions in the program.

## Retrieve all transactions for a given sender
Endpoint - ```GET http://localhost:8080/transactions/{senderId}?onlyActive=false```
- Retrieves all transactions for a given sender account.
- OnlyActive flag will retrieve all non-cancelled transactions if set to true.

## Cancel a transaction
Endpoint - ```DELETE http://localhost:8080/transactions/{transactionId}/cancel```
- Cancels a transaction in the program
- The cancelled transaction will be soft-deleted. Only the transactionStatus field will be set to cancelled but the record will remain in the program.