# SeleniumFrameworkDesign

This is a hybrid automation framework built using **Selenium WebDriver**, **TestNG**, **Page Object Model (POM)**, and **Maven**. The framework is designed to enable scalable, maintainable, and cross-browser UI automation testing.

## 📁 Project Structure

SeleniumFrameworkDesign/
│
├── src/
│ ├── main/
│ │ └── java/
│ │ └── org/seleniumDesign/
│ │ ├── BaseClass.java # WebDriver initialization and setup
│ │ ├── ConfigReader.java # Reads config from properties file
│ │ ├── PageObjects/ # POM classes for different pages
│ │
│ └── test/
│ └── java/
│ └── org/testCases/
│ ├── LoginTest.java # Sample test case
│ ├── HomePageTest.java # Additional test cases
│
├── testng.xml # TestNG suite configuration
├── config.properties # Application and environment settings
├── pom.xml # Maven dependencies and build setup
└── README.md

markdown
Copy
Edit

## ✅ Key Features

- **Cross-Browser Testing:** Supports Chrome and Edge.
- **Maven Integration:** Easy dependency management and build configuration.
- **TestNG Framework:** Test suite management with parallel execution capabilities.
- **Page Object Model (POM):** Clean separation of page elements and logic.
- **Reusable Components:** `BaseClass.java` handles WebDriver setup and teardown.
- **Configuration Driven:** Easily switch settings using `config.properties`.


---

## 🧪 Features

- ✅ Modular Page Object Model (POM)
- ✅ Centralized configuration file
- ✅ Supports multiple browsers
- ✅ Parallel execution using TestNG
- ✅ Maven build automation
- ✅ Easy-to-extend test structure

---

## ⚙️ Prerequisites

- Java 8 or higher
- Maven 3+
- Latest ChromeDriver and/or EdgeDriver added to system path

---

## 🚀 How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Ashok161-bit/SeleniumFrameworkDesign.git
   cd SeleniumFrameworkDesign
