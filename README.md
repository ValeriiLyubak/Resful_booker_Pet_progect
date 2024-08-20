Restful-booker API

ENG
/ [RU](README_ru.md)


Overview

This project encapsulates a testing framework that is implemented using Java. It is designed to facilitate functional testing of the backend API for the hotel booking web service available at restful-booker.herokuapp.com. The primary aim of this project is to showcase proficiency in backend testing and the development of a framework that leverages tools and libraries like Maven, Git, Allure, RestAssured, Lombok, TestNG, and Jackson.


Functionality
 - Functional Testing: The framework conducts thorough inspections of the fundamental features provided by the web service. These inspections are based on the site's documentation which includes functionalities like authorization, creation/modification/deletion of bookings, retrieving booking information, and performing system health checks.
 - Reporting: The framework is configured to integrate seamlessly with Allure, allowing users to generate comprehensive, visually-appealing reports detailing the test executions.


Technologies
 - Programming Language: Java.
 - Building and Project Management: Maven.
 - Testing Libraries: TestNG, RestAssured.
 - Logging Framework: Logback.
 - JSON Serialization and Deserialization: Jackson.
 - Generating Auxiliary Functions in Java Classes: Lombok.
 - Automatic Loading of Configurations: Owner.
 - Logging Library: SLF4J.
 - Random Data Generation: JavaFaker.
 - Version Control: Git.
 - Test Reports: Allure.


Getting Started

1. Clone the Repository: Use the command - git clone https://github.com/ValeriiLyubak/Resful_booker_Pet_progect.
2. Install Dependencies: You can install the necessary dependencies for the project by using the mvn install command.
3. Test Execution: The project comes with a pre-configured test execution sequence that utilizes TestNG in the testng.xml file. You can execute all the tests in a sequence using the mvn clean test command.
4. Allure Report Generation: You can create Allure reports using the command - allure generate allure-results --clean -o allure-report.