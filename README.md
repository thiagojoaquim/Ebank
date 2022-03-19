# Ebank - Backend



## About Ebank

Ebank is a BaaS project developed for Ebanx hiring test.


## About Backend

### Languages and Frameworks

- [Java](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
- [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Gradle](https://gradle.org/)
- [Heroku](https://www.heroku.com/about)

### Architecture

For architecture, the concepts presented by Robert Martin in the book Arquitetura Limpa were applied. In the figure below, we have the layers used in the project.

![image](https://user-images.githubusercontent.com/42274127/125374629-ca464a80-e35d-11eb-842d-b292bc12181c.png)

#### Architecture Rules

As the architecture was designed based on the concepts presented in the Clean Architecture book, the SOLID principles are at the heart of the implementation. So below is a set of rules set for implementation.
- **Dependency must be from the external layers to the inside layers:** As presented in clean architecture, the architecture must depend only on what really matters, the domain. Therefore, the domain layer must not contain dependencies with other layers, avoiding the coupling of business rules with application or framework rules.
- **Layers communicate through Interfaces:** The "D" principle of SOLID says that classes with strong dependencies should depend on abstraction, not implementation. Therefore, any and all communication between layers is done through interfaces, enabling the "O" principle of SOLID, where a class must be opened for extension and closed for modification.
- **Nobody should depend on the outermost layer:** Just as the innermost layer shouldn't depend on anyone, nobody should depend on the outermost class, as frameworks, database connections and other tools reside there, which leads to a greater volatility. 
