# InsuranceClaimsPlatform
An Insurance Claims Platform for the AAA Life coding assessment

The application is built using Java, Spring Boot, and an in-memory H2 database for simplicity and ease of testing.

For simplicity, claim documents are not included in this implementation.

## Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 In-Memory Database
- Maven

## Installation

Clone the repository:
```
git clone <repository-url>
cd InsuranceClaimsPlatform
```
Build the project:
```
mvn clean install
```
---

## Running the Application

Start the API server:
```
mvn spring-boot:run
```

The server will start on: `http://localhost:8080`

## API Endpoints

### Submit a Claim
`POST /claims`

Example:
```
curl -X POST http://localhost:8080/claims -H "Content-Type: application/json" -d '{
    "customerId": "CUST123",
    "policyId": "POL456",
    "claimType": "medical",
    "description": "Emergency room visit"
}'
```

### Retrieve Claims for a Customer
`GET /claims/customer/{customerId}`

Example
```
curl http://localhost:8080/claims/customer/CUST123
```

### Update Claim Status
`PATCH /claims/{claimId}`
```
curl -X PATCH http://localhost:8080/claims/{claimId} -H "Content-Type: application/json" -d '{
    "status": "APPROVED"
}'
```


Allowed statuses:

- `SUBMITTED`
- `APPROVED`
- `DENIED`

### Retrieve Duplicate Claims

Claims are flagged as duplicates if a claim is submitted with the same
`customerId` and `policyId` as an existing claim.

`GET /claims/duplicates``

Example:
```
curl http://localhost:8080/claims/duplicates
```