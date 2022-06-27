# Capstone Project: FitLy
![image](https://github.com/aewing7/FitLy/blob/master/src/main/resources/static/pictures/AltLogoTextIcon.png?raw=true)
## Created By Aaron Ewing

### GitHub Repository Link: https://github.com/aewing7/FitLy

### ATTN: Server port is set to 8081, use http://localhost:8081/ to view project or change the port in the application.properties located in the resources folder

### Introduction:

Fitly is a fitness tracker web app that can track your exercises, food and drink and goals throughout your fitness journey. 

This project was created as my capstone for my TekSystems training cohort 


### How to use:

Download the project from the repository and import it into your IDE. Make sure you set your database properties to your information so the project has a database to access and edit. The default database in the application.properties is "capstone" but can be edited to any database you want to map it to.

data.sql will run on program start and will populate the specified database with the required data. This data.sql file should run automatically and will be ignored if the data is already added into your database.

To use the web app, create an account using the sign-up button. From there, you will be redirected back to the home page and will be able to sign in and will be taken to your home page. From the home page you can decide what you want to track from exercises, food(and drink) and goals or view the about page which tells you how to contact me! 

Below, I have the requirements and where they can be seen/found on the page and in the code (If applicable).


## Requirements:

<details><summary>

### Project Structure, Standardizatrion, and Conventions 

</summary>

- The project package structure should be shown in class where the models, DAO/repositories, services, controllers, exceptions, etc., have a package. Views or templates do not require a package (Found in main folder)
- Each class should include comments to describe the class and the methods (Found in classes)
- Have the project pushed into GitHub from the early stage of development and hosted on GitHub with a “readme” file documenting an overview of your project (https://github.com/aewing7/FitLy)
</details>

<details><summary>

### Core Java and Models

</summary>

- Utilize Java classes with constant variables (i.e., variables that never change from their initial value). The value of these variables can be requested parameters, SQL queries used in the DAO, names of HTML pages, or URL patterns to forward a request to (Found throughout project)
- Have at least four models and corresponding tables in a relational database (if four models/tables do not make sense for your application, discuss this with your instructor) (Found in models package)
- At least four models (Found in models package)
- Apply exception handling (Exceptions found in exception package and exceptions thrown in controllers)

</details>

<details><summary>

### Database, ORM, and Hibernate

</summary>

- Use MariaDB as your DBMS (check with your instructor if you need support to install MariaDB on your computer) (Used mariaDB)
- Include a schema diagram of the tables and the SQL you used for the database (Schema found in schema folder in project root)
- The database configuration file must be set up correctly in your Spring application through “spring initializr” (in resources)
- Include at least three custom queries (Queries found in repositories)
- Use Hibernate or Jakarta Persistence API (JPA) directly or through Spring Data JPA (Used Hibernate and Spring Data JPA)
- Your application should include examples for all four CRUD operations (Create, Read, Update, and Delete) - (Found in every controller, can be seen running the app)

</details>

<details><summary>

### Front-end Development

</summary>

- Use CSS to style the Web pages. Use an external CSS stylesheet (internal styling may be used along with frameworks such as Bootstrap, but you must still include and utilize a custom CSS external file) (Css files found in resources/static/css files)
- Your application should include six different views/pages (HTML views can be found in resources)
- Use HTML to lay out the pages and Thymeleaf to make the pages dynamic (Frameworks such as Angular or React can also be used but will not be covered in the course. Both Angular or React are optional.). The application’s presentation must meet the general view requirements. (Found throughout the .html files)
- Use at least one JavaScript script linked from an external script file (Internal scripts may be used along with frameworks such as jQuery, but you must still include and utilize a custom JavaScript external file) (JS found in resources/static/js files)
- Include a navigation section that is included across multiple pages (Navbar found at the top of all pages once logged in)


</details>

<details><summary>

### Spring Framework

</summary>

- Use Spring Boot to develop your project (Completed)
- Models should be annotated for binding using Spring data binding through Jakarta and/or Hibernate validation (Found in models package)
- Include and implement at least two repositories and two service classes/interfaces (Found in service package and repository package)
- Include at least two ways of creating a managed bean/object (Completed)
- Use correct implementations of dependency injection with appropriate use of the @Autowired annotation (Completed)
- Include at least one example of session management (Spring Security can be used for session management) (Using Spring Security for session management)
- Use Transaction and request/response logging (write log to a file) (logging find in the root)
Include sign-up and login functionality with encrypted passwords using bcrypt (Spring Security will satisfy this requirement) (Completed with Spring Security)


</details>

<details><summary>

### Unit Testing

</summary>

- Test each query created in the repositories (Found in test package)
- Test at least one method in each service class (Found in test package)

</details>

<details><summary>

## TECHONOLOGIES USED:

</summary>

- MariaDB
- Hibernate
- Spring Data
- Java
- Spring Boot
- Spring Security
- HTML
- CSS
- Bootstrap
- JavaScript
- Thymeleaf

</details>

<details><summary>

### User Stories:

</summary>

1.	As a user, I want to be able to enter in my specific workout so that I can track which workouts ive done
2.	As a user, I want to be able to enter in my specific reps and weight for my workouts, so I can track my weekly progress
3.	As a user, I want to be able to see my entered workouts whenever I log in, so I can track my workout data
4.	As a user, I want to be able to set my account details (Name, Height, Weight) so that I can personalize my account
5.	As a user, I want to be able to log in so that I can see my saved data
6.	As a user, I want to be able to enter the food I have eaten, so I can track it.
7.	As a user, I want to be able to enter the calories for the food I ate, so I can track my daily intake.
8.	As a user, I want to be able to enter my servings for the food I ate to track the servings.
9.	As a user, I want to be able to sort my workouts by week so that I can see what I do week to week
10.	As a user, I want to be able to sort my food by daily and weekly so that I can track my eating habits.
11.	As a user, I want to be able to set future goals, so I will have reminders of what I want to work for.
12.	As a user, I want to be able to enter a specific number for sets I completed to track that along with my reps.

</details>



