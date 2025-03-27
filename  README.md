# 📚 Digital Library Book Management System

## Overview
This project is a console-based **Digital Library Book Management System** that allows librarians to **add, view, search, update, and delete books** from the library collection. The system ensures that each book has a **unique ID** and valid information for **title, author, and availability status**.

---

## 🚀 Features
- ✅ **Add a Book** – Enter book details including **Book ID, Title, Author, Genre, and Availability Status**.
- 📖 **View All Books** – Display a list of all books.
- 🔍 **Search Book** – Search for a book by **ID or Title**.
- ✏️ **Update Book** – Modify the details of an existing book.
- ❌ **Delete Book** – Remove a book from the system.
- 🔚 **Exit System** – Close the application gracefully.

---

### 📌 Notes **Challenges Faced**
1. **Input Validation Complexity**
   - Ensuring `Book ID` uniqueness required careful state management.
   - **Solution:** Implemented a **`HashMap`** for **O(1)** lookups.

2. **Search Functionality**
   - Case-insensitive title search needed normalization (`toLowerCase()`).
   - **Trade-off:** Increased memory usage for faster searches.

3. **Maven Configuration**
   - Initial struggles with `pom.xml` dependencies.
   - **Solution:** Learned to use the **Maven Wrapper (`mvnw`)** for consistency.

---

## 🚀 **Potential Improvements If Given More Time**
1️⃣ **Upgrade to a REST API**
- Convert the console-based system into a **full-fledged REST API** using **Spring Boot**.
- Provide endpoints for adding, updating, deleting, and retrieving books.

2️⃣ **Implement JWT Authentication & Role-Based Access**
- Add **Spring Security** with **JWT authentication**.
- Define roles such as `ADMIN` and `USER` for better access control.

3️⃣ **Database Integration**
- Replace the in-memory storage with a **relational database** like **MySQL/PostgreSQL** using **Spring Data JPA**.

4️⃣ **Enhance User Interface**
- Develop a **frontend using React or Angular**.
- Provide a **web-based dashboard** for managing books.

5️⃣ **Logging & Monitoring**
- Implement **Spring Boot Actuator** for system monitoring.
- Add **Logback/SLF4J** for logging errors and activity.

6️⃣ **Deploy Using Docker & Kubernetes**
- Containerize the application using **Docker**.
- Deploy it to **Kubernetes (K8s) on AWS/GCP** for scalability.

7️⃣ **AI-Based Book Recommendations**
- Use **Machine Learning** to suggest books based on user preferences.
- Implement a **recommendation engine** using **Python (Scikit-Learn/TensorFlow)**.

---

## 🛠️ **Setup and Run Instructions**

### 🔹 1️⃣ **Prerequisites**
- **Java JDK 8 or higher**
- **Maven** (for building and running tests)

### 🔹 2️⃣ **Run the Application**
#### Option 1: Download Pre-built JAR
1. Download the latest release from:  
   [GitHub Releases](https://github.com/DEVESH12000/Digital-Library-Book-Management-System/releases/latest)
2. Run using Java:
   ```bash
   java -jar dlbms-1.0.0.jar