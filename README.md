# 📚 Library Management System

A full-stack web application built with Java EE to manage books, members, and borrowing records in a library.

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 11 |
| Web Layer | Servlets + JSP |
| ORM | JPA + Hibernate |
| Architecture | MVC |
| Database | MySQL |
| Build Tool | Maven |
| Server | Apache Tomcat 10.1 |
| IDE | Eclipse |

---

## ✨ Features

- 📖 **Book Management** — Add, edit, delete, and search books with copy tracking
- 👤 **Member Management** — Register members (Student / Faculty / Public)
- 🔄 **Issue & Return** — Issue books to members with auto 14-day due dates
- 📊 **Availability Tracking** — Real-time available copies counter

---

## 🏗️ Project Structure

```
LibraryMS/
├── pom.xml
└── src/main/
    ├── java/com/library/
    │   ├── model/         # Book.java, Member.java, BorrowRecord.java
    │   ├── dao/           # BookDAO.java, MemberDAO.java, BorrowRecordDAO.java
    │   ├── servlet/       # BookServlet.java, MemberServlet.java, BorrowServlet.java
    │   └── util/          # JPAUtil.java
    ├── resources/
    │   └── META-INF/
    │       └── persistence.xml
    └── webapp/
        ├── index.jsp
        ├── css/
        │   └── style.css
        └── WEB-INF/
            ├── web.xml
            └── views/
                ├── books.jsp
                ├── book-form.jsp
                ├── members.jsp
                ├── member-form.jsp
                ├── borrow-list.jsp
                └── borrow-form.jsp
```

---

## ⚙️ MVC Flow

```
Browser Request
     ↓
Servlet (Controller)
     ↓
DAO + JPA/Hibernate (Model)
     ↓
MySQL Database
     ↓
JSP Page (View)
     ↓
Browser Response
```

---

## 🚀 Getting Started

### Prerequisites
- Java 11+
- MySQL 8+
- Apache Tomcat 10.1+
- Maven 3+
- Eclipse IDE for Enterprise Java Developers

### 1. Clone the Repository
```bash
git clone https://github.com/Narendrakumar432/LibraryManagementSystem.git
cd LibraryManagementSystem
```

### 2. Create the Database
```sql
CREATE DATABASE library_db;
```

### 3. Configure Database Password
Open `src/main/resources/META-INF/persistence.xml` and update:
```xml
<property name="jakarta.persistence.jdbc.password" value="your_mysql_password"/>
```

### 4. Build the Project
```bash
mvn clean package
```

### 5. Deploy to Tomcat
- Import project into Eclipse as **Maven → Existing Maven Project**
- Add to Tomcat 10.1 server via **Servers tab → Add and Remove**
- Start Tomcat and open:
```
http://localhost:8080/LibraryManagementSystem/
```

---

## 📸 Screenshots

### Dashboard
![Dashboard](s<img width="1920" height="1080" alt="Screenshot 2026-03-31 174615" src="https://github.com/user-attachments/assets/c66e1411-ca47-4360-8452-a81b293df1c8" />
creenshots/dashboard.png)

### Books Management
![Books](scr<img width="1920" height="1080" alt="Screenshot 2026-03-31 223848" src="https://github.com/user-attachments/assets/40ecd807-7388-4ecd-8558-f9d82b3ca445" />
eenshots/books.png)

### Members Management
![Members](screenshots/mem<img width="1920" height="1080" alt="Screenshot 2026-03-31 223952" src="https://github.com/user-attachments/assets/4f503241-3d09-4f7a-aee0-68429d01e37d" />
bers.png)

### Issue Book
![Borrow](scre<img width="1920" height="1080" alt="Screenshot 2026-03-31 224012" src="https://github.com/user-attachments/assets/3817c93a-a97d-4fc1-8ea2-9418f2981798" />
enshots/borrow.png)

---

## 📝 How It Works

1. **Add Books** — Enter title, author, ISBN, genre, and number of copies
2. **Register Members** — Add members with Student / Faculty / Public type
3. **Issue a Book** — Select a book and member; due date is auto-set to 14 days
4. **Return a Book** — Mark the book as returned; copy count updates automatically

---

## 🤝 Connect

**Narendra Kumar**
- LinkedIn: [linkedin.com/in/narendrakumar](https://linkedin.com/in/narendrakumar)
- GitHub: [github.com/Narendrakumar432](https://github.com/Narendrakumar432)
