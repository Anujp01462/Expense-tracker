# 💰 Personal Expense & Budget Tracker

A modern, full-stack personal finance and expense tracking web application built with **Spring Boot** and a sleek, responsive front-end dashboard.

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen?style=flat-square&logo=springboot)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-purple?style=flat-square&logo=bootstrap)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)

---

## 🌟 Key Features

- **📊 Real-time Financial Dashboard**:
  - Instant calculation of **Total Income**, **Total Expenses**, and **Net Balance**.
  - Visual category breakdown showing expenditure distribution.
- **💸 Transaction Management (Full CRUD)**:
  - Add, view, edit, and delete both **Income** and **Expense** entries.
  - Automatically timestamped records with support for custom dates.
- **🔍 Smart Filtering**:
  - Filter transactions seamlessly by **Category** (Food, Transport, Utilities, Entertainment, Salary, Other) and **Type** (Income / Expense).
- **🎨 Modern & Responsive UI**:
  - Clean interface built with Bootstrap 5, FontAwesome icons, and the Inter typography.
  - Works on desktops, tablets, and mobile devices.
- **⚡ Thread-Safe In-Memory Architecture**:
  - Fast, zero-config startup using concurrent in-memory data structures (`ConcurrentHashMap`, `AtomicLong`).
  - Pre-seeded with sample transactions so you can explore immediately.

---

## 🛠️ Tech Stack

### Backend
- **Language**: Java 21
- **Framework**: Spring Boot 4.1.0
- **Web**: Spring MVC (RESTful API)
- **Build Tool**: Maven

### Frontend
- **Markup & Styling**: HTML5, Vanilla CSS3, Bootstrap 5.3
- **Icons & Typography**: FontAwesome 6, Google Fonts (Inter)
- **Logic**: Vanilla JavaScript (Async/Await Fetch API)

---

## 📁 Project Structure

```text
expense-tracker/
├── pom.xml                                    # Maven configuration & dependencies
├── README.md                                  # Project documentation
├── .gitignore                                 # Git ignore file
└── src/
    └── main/
        ├── java/com/example/expensetracker/
        │   ├── ExpenseApplication.java        # Spring Boot entry point
        │   ├── controller/
        │   │   └── ExpenseController.java     # REST API Controller
        │   ├── model/
        │   │   └── Expense.java               # Expense / Income entity model
        │   └── service/
        │       └── ExpenseService.java        # Business logic & in-memory data store
        └── resources/
            ├── application.properties         # Server & app configurations (Port 8082)
            └── static/
                └── index.html                 # Interactive single-page dashboard
```

---

## 🚀 Getting Started

### Prerequisites
Make sure you have installed:
- **Java Development Kit (JDK) 21** or higher: `java -version`
- **Apache Maven 3.8+** (or use your IDE's built-in Maven): `mvn -version`

### Installation & Running

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Anujp01462/Expense-tracker.git
   cd Expense-tracker
   ```

2. **Build and run the application**:
   - Using Maven CLI:
     ```bash
     mvn spring-boot:run
     ```
   - Or open the project in your favorite IDE (IntelliJ IDEA, Eclipse, VS Code) and run `ExpenseApplication.java`.

3. **Access the application**:
   Open your browser and navigate to:
   ```text
   http://localhost:8082
   ```

---

## 📡 REST API Documentation

The application exposes the following RESTful endpoints under `/api/expenses`:

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/expenses` | Retrieve all transactions (supports `?category=...&type=...`) |
| `GET` | `/api/expenses/{id}` | Get details of a single transaction by ID |
| `POST` | `/api/expenses` | Create a new transaction |
| `PUT` | `/api/expenses/{id}` | Update an existing transaction |
| `DELETE` | `/api/expenses/{id}` | Delete a transaction by ID |
| `GET` | `/api/expenses/summary` | Get financial summary (total income, expense, balance, category breakdown) |

### Sample JSON Request (Add Transaction)
```json
POST /api/expenses
Content-Type: application/json

{
  "title": "Grocery Shopping",
  "amount": 2500.0,
  "category": "Food",
  "type": "EXPENSE",
  "date": "2026-09-28"
}
```

### Sample Summary Response
```json
GET /api/expenses/summary

{
  "totalIncome": 45000.0,
  "totalExpense": 12200.0,
  "balance": 32800.0,
  "categoryBreakdown": {
    "Food": 6500.0,
    "Utilities": 1200.0,
    "Entertainment": 3500.0,
    "Transport": 1000.0
  }
}
```

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!
1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
