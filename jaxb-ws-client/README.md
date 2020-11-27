

**JAXWS Sample WS-Client**
---
- this example is configued with Java 11, which does not have wsimport
- hence using jaxws maven plugin, details can be found in references
- `mvn clean jaxws:wsimport` - will generate the classes.

**Execute:**
- `mvn compile ; mvn exec:java -Dexec.mainClass="com.java.app.App"`

**References:-**
- https://www.baeldung.com/jax-ws#1-generating-web-service-source-files-from-wsdl
- https://www.baeldung.com/java-soap-web-service#maven-wsimport
