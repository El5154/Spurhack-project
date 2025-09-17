# Spurhack Project

A Java-based application for managing and analyzing financial transactions.  
Built as part of the Spurhack hackathon project, this app helps track **spending, revenue, and expenses** with a simple interface and persistent storage.

---

## Features

- Add, view, and manage financial transactions  
- Separate categories for spending and revenue  
- Persistent storage via a text-file transaction database  
- Simple user interface for interaction  
- Basic financial summaries and reporting  

---

## Project Structure

| File | Description |
|------|-------------|
| `Transaction.java`        | Defines a single transaction (amount, category, date, etc.) |
| `Spending.java`           | Manages spending/expense entries                            |
| `TransactionManager.java` | Core logic for managing, saving, and loading transactions   |
| `TransactionDatabase.txt` | Plain text file used as a transaction database              |
| `UserInterface.java`      | Entry point / user interaction layer                        |
| `GeminiAdvisor.java`      | Provides insights or advice based on transaction data       |
| `Revenue&Expense.png`     | Diagram or screenshot showing revenue vs. expense overview  |

---

## Usage

1. Launch the application
2. Add transactions (spending or revenue)
3. View all transactions
4. Get summaries of your financial activity
5. Data is saved and loaded automatically from TransactionDatabase.txt

---

## Authors

@El5154
@xiandaniel
@LodeModeCode
