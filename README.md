# Project Title

* Wall for employees for posting messages.
* Follow other employees.
* Authentication using LDAP

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project.

### Prerequisites

Need gradle to run the application

With [Homebrew] (https://brew.sh/)

```
$ brew update && brew install gradle
```

## Running the application

```
$ ./gradlew build && java -jar build/libs/wall-assignment-0.0.1-SNAPSHOT.jar
```

OR
```
$ ./gradlew clean bootRun
```
and then visit

```
http://localhost:8080/loginForm
```
to login to the application and 
```
http://localhost:8080/console
```
to see H2 console. Enter JDBC Url as "jdbc:h2:~/WallDB" and click connect.


## Deployment

Spring Boot automatically configures embedded Tomcat.

## Built With

* [gradle](https://gradle.org/) - Source build automation system

## Acknowledgments

* [Spring Boot] (https://spring.io/guides/gs/spring-boot/)
