# MDD application
MDD is a social network. Users can post articles about software development. They can subscribe/unsubscribe to subjects. They can post comments on articles.

## Technologies
- Java
- SpringBoot
- MySQLServer
- Angular 14
- HTML
- CSS

## SQL setup
- Install MySQL Server and MYSQL Workbench
- Take note of your user name, your password and the port number for MySQL
- From the Workbench, create a new schema named 'mdd'
- Right-click on your schema and select it as default schema
- Open one of the two following files and copy its content:
- script without samples: \Developpez-une-application-full-stack-complete\back\src\main\resources\mdd.sql
- or script with samples: \Developpez-une-application-full-stack-complete\back\src\main\resources\mdd-samples.sql
- From the Workbench, open a new query tab and paste all the copied lines. Select all and execute.

## Back-end project setup
- Clone this project https://github.com/fguyont/Developpez-une-application-full-stack-complete.git
- Go to this file back/src/main/resources/application.properties
- For the line spring.datasource.url and after = , get this line jdbc:mysql://localhost:PORT/mdd by replacing PORT by the SQL port
- For the line spring.datasource.username=, insert your SQL user name
- Same thing with the line spring.datasource.password= with your password
- By the console enter the 'back' folder
- type 'mvn install' 

## Front-end project setup
- By the console enter the 'front' folder
- Enter 'npm install'

## Application run
- For the back-end project, in back folder type: 'mvn spring-boot:run'
- For the front-end project, in front folder, type 'ng serve'
- Navigate to 'http://localhost:4200/'

## At the beginning
- You will need to create a new user by clicking on 'S'inscrire' button from the default page
- Insert a username, an email address and a password
- The password must contain at least 8 characters including at least 1 uppercase character, at least 1 numeric character and at least 1 special character

## Author

Frédéric Guyont

