# BANKKATA
SpringBoot application Kata for some  Banking Operation



This is a simple Bank Account API built using Spring Boot as a coding exercise (kata). The API allows clients to deposit, withdraw money from their bank account, and retrieve their transaction history and current balance.

# Features


Deposit money into the bank account
Withdraw money from the bank account
View transaction history (operation type, date, amount, balance after operation)
Check current balance


# Endpoints



# /api/account/deposit Deposit money into the account. Requires amount as a query param.
# /api/account/withdraw  Withdraw money from the account. Requires amount as a query param.
# /api/account/statement Retrieve the list of all deposit/withdrawal operations performed on the account.
# /api/account/balance Retrieve the current account balance.
