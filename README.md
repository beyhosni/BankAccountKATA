# Bank Kata API

## Description

This project is a simple **Bank Account API** built using **Spring Boot** as a coding exercise (kata). The API allows clients to perform operations like depositing money, withdrawing money, checking their transaction history, and viewing their current account balance.

The application is developed using a **TDD (Test-Driven Development)** approach, without persistence (no database) and no user interface (UI). API versioning is handled through the URL paths.

## Features

- **Deposit**: Add money to your bank account.
- **Withdraw**: Withdraw money from your bank account.
- **Transaction history**: View a list of all operations (type, date, amount, balance after operation).
- **Current balance**: Check the current balance of your bank account.

## API Versioning

The API uses versioning in the URL paths. The current version is **v1**. All requests should include the version in the URL path, such as `/api/v1/account`.

## Endpoints

### Version 1 API (`/api/v1`)

- **POST** `/api/v1/account/deposit`  
  Deposit money into the account. Requires `amount` as a query parameter.

  **Example**:  


- **POST** `/api/v1/account/withdraw`  
Withdraw money from the account. Requires `amount` as a query parameter.

**Example**:  


- **GET** `/api/v1/account/statement`  
Retrieve a list of all deposit/withdrawal operations performed on the account.

**Example**:  


- **GET** `/api/v1/account/balance`  
Retrieve the current account balance.

**Example**:  


## Prerequisites

- Java 21 or higher
- Maven or Gradle


