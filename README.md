<div style="display: inline_block">
    <img align="center" alt="Mateus-Spring" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg"/>
    <img align="center" alt="Mateus-Java" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg"/>
    <img align="center" alt="Mateus-Mysql" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original.svg"/>
</div>
<br>

# Shop API

This is the backend api for the DEMO SHOP project.

## Build

To build this application run the following command in the root directory of the project:

```shell script
docker build . -t shop-api:latest
```  

## Tests

Tests on this application have been developed with JUnit, Mockito.  
To run the tests we just use this command:

```shell script
mvn test
```

To run a single Test Suite just use the following command replacing the placeholder  
TEST_CLASS with  the Test Suite you want to run:
```shell script
 mvn -Dtest=TEST_CLASS test
```
