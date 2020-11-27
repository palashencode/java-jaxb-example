

**Notes:** - JaxB examples and demos.
---
- This project contains examples of using JAXB, along with sample xmls and xsds.
- JAXB allows easy conversion from/to xsd to java classes, and also validation.
- validation can be customized using custom event handlers
- Java to XML - generation can be customized using @Annotations
- XSD to Java - generation can be customized using bindings.xjb file, and xsd structure itself.
- JAXB is also the foundation in using JAX-WS
- schemagen and xjc tools are no longer part of the jdk 11, but schema generation can be done programatically.

**Code**
---
- `com.java.generate_schema` - schema generation from java classes
- `com.java.simple_marshall_unmarshall` - marshalling and unmarshalling examples.

**Study Notes:**
---
JAXB Annotation (some of the annotations used are) - 
- `@XMLRootElement`
- `@XMLAccessorType`
- `@XmlElement ( name = "item" , defaultValue = "", required = true )` // the required setting does not cause error during marshalling/unmarshalling, no validation by default.
- `@XmlElementWrapper ( name = "items" )`
- `@XmlTransient`
- `@XmlAttribute`
- `@XmlAccessorOrder`
- `@XmlType ( propOrder = { "name", "loyalty", "shippingAddress"} )`
- `@XmlSchemaType ( name = "date" )`
	- `@XmlSchemaType ( type = Date.class, name = "date" )`
- `@XmlValue` - make the class -> map to a single attribute, to be used only one field, rest should be `@XmlTransient` or  `@XmlAttribute`
- `@XmlEnum(Integer.class)`
- `@XmlEnumValue ("10") BRONZE`
- `@XmlJavaTypeAdapter ( ItemsAdapter.class )` 
	- `ItemsAdapter.class` extends `XMLAdapter<ItemsWrapper, Map<String,Item>>`
	- item gets marshalled to an ItemsWrapper
- `@XmlRegistry`
- `@XmlElementDecl`
- `@XmlElementRef`
- `@XmlSeeAlso({ ShippingAddress.class, BillingAddress.class})` - put in super classes for complex xsd types.
- `@XmlAnyElement (lax = true)`



**Compatibility with JDK11**
---
- JAXB has been removed from JDK11, hence using maven imports

**Execute:**
---
- `mvn compile ; mvn exec:java -Dexec.mainClass="com.java.app.App"`

**References:**
---
- PluralSight Course - Working with XML in Java Using JAXB By Jesper De Jong
    - https://app.pluralsight.com/library/courses/xml-java-using-jaxb/table-of-contents
