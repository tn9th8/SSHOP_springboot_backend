# E-commerce RESTful API Backend

This is a RESTful API built with Spring Boot 3 for an e-commerce platform similar to TikTok Shop. It supports various functionalities such as product management, user management, order processing, and more // todo: continue README.

## How to run
- java -jar SSHOP_springboot_backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=[dev]

## Technology
- Spring Boot 3.4.0 (Rest, Data JPA, Security)
- PostgreSQL 16.6.0

Spring Data JPA
- Methods
- Query
  - use JPQL command, allow to pass params
  - allow to Select, Modify (can update, delete; but can't create)

Spring Security:
- Authentication base on JWT
- Token follows the OAuth2 standard to Spring Security map claims of payload to fields of authentication
    - subject to name
    - scope to authorities[].authority
      - authority have a default prefix "SCOPE_", then system will config to convert to "ROLE_", example: ROLE_ADMIN
- Authorization base on Role
  - use the hasAuthority("ROLE_ADMIN") or hasRole("ADMIN") methods at Security Filter Chain tier
  - use the PreAuthority, PostAuthority annotation at Methods of Service tier
- Handle exception at Security Filter Chain tier

## Project
- Yaml: is a config file which includes environment variables
  - follows tree and key-value structures
  - provides various data types (int, string, boolean, map, ...) 
  - configs at multiple environments (dev, test, pre-release, product)
- Maven: is a project management tool, has xml type
- Server: localhost
- Port: 8044
- Context-path: sshop
- Jar: is a format used for packaging and deploying applications
  - run command: java -jar SSHOP_springboot_backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=[dev]

Api document
- The project uses Swagger from Open-api to manage api list. Link: http://localhost:8044/sshop/swagger-ui/index.html
- Postman: //todo

Folder structure:
- Multi module (being applied)
- Single module

Git rule
- For new future, please set the branch with name: "features/{features-description}" for the new branch name.
- For fix bug, please set the branch with name: "fix/{bug-description}" for the new branch name.

Reference:
- Spring Security: https://docs.spring.io/spring-security/reference/
- Spring Data JPA: https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#reference
- JPQL: https://docs.oracle.com/html/E13946_04/ejb3_langref.html