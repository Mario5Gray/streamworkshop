# Start with Spring

Lets start by going to [start-dot-spring-dot-io](https://start.spring.io) and bring together a Java Maven Spring Boot 2.5.* project with metadata having the following settings:


 | Field    | Value |
 |------------|-------|
 | Group | com.example |
 | Artifact | domain |
 | Name | streamworkshop |
 | description| domain | 
 | Package name | com.example.streamworkshop |


### Download via HTTP

Going to the website via [This link](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.5.4&packaging=jar&jvmVersion=11&groupId=com.example.streamworkshop&artifactId=domain&name=domain&description=Domian%20and%20Service-contract%20level%20abstractions&packageName=com.example.streamworkshop&dependencies=lombok) will give you the configuration options in place.

Alternatively, start.spring.io is accessible with our favorite commandline utility `curl`. Calling the site with the command `curl -L start.spring.io` gives a fair amount of argument descriptions and invokations styles. The same artifact can be downloaded on the (UNIX) commandline with the following invokation.

```bash
 curl -L start.spring.io/starter.zip -d language=java -d platformVersion=2.5.4 -d packaging=jar -d jvmVersion=11 -d groupId=com.example.streamworkshop -d artifactId=domain -d name=domain -d description='Domain and service contract abstractions' -d packageName=com.example.streamworkshop -d dependencies=lombok
 ```

## Code the domain strategy

We have the following 5 domain states:

[Stock]()

This class holds price information on a 4 letter code.

[User]()

This data holds usernames for login.

[StockTick]()

This is data-intransit - aggregate updates applies to the `stock` class.

[StockSubscription]()

## Code the service interfaces

We have the following services:

[StockQuoteService]()

This service grabs up-to-date information on a known symbol represented by the `Stock` class.

[StockTickService]()

Here, we find deliver pub-sub streams containing just `StockTick` data.

[TradeService]()

We utilize background batch jobs in order to translate `Stock` data.

[UserService]()

Possibly, we have logins which maintain not just sessions, but user preferences such as hold/trade lists.

[UserAuthenticationService]()

For the UserService to deliver presence in a sesion, we need to use Spring Security.

## Testing the Domain

Standard JUnit and AssertJ apply here. Just make sure we have the desired state for each object, and that object creation works accordingly.



## Testing Services

## Deliverables

We may also want to export a Jar file for re-using some of our test logic in other projects.