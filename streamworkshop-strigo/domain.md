# Start with Spring

Lets start by going to [start-dot-spring-dot-io](https://start.spring.io) and bring together a Java Maven Spring Boot 2.5.* project with metadata having the following settings:


 | Field    | Value |
 |------------|-------|
 | Group | com.example |
 | Artifact | domain |
 | Name | streamworkshop |
 | description| domain | 
 | Package name | com.example.streamworkshop |


The following curl command works as well: 


Going to the website via [This link](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.5.4&packaging=jar&jvmVersion=11&groupId=com.example.streamworkshop&artifactId=domain&name=domain&description=Domian%20and%20Service-contract%20level%20abstractions&packageName=com.example.streamworkshop&dependencies=lombok) will give you the configuration options in place.

Alternatively, start.spring.io is accessible with our favorite commandline utility `curl`. calling the site gives a fair amount of information on option choices and argument passing. The same artifact can be downloaded on the (UNIX) commandline with the following invokation.

```bash
 curl -L start.spring.io/starter.zip -d language=java -d platformVersion=2.5.4 -d packaging=jar -d jvmVersion=11 -d groupId=com.example.streamworkshop -d artifactId=domain -d name=domain -d description='Domain and service contract abstractions' -d packageName=com.example.streamworkshop -d dependencies=lombok```


## Code the domain strategy

We have the following 5 domain states:

[Stock]()

[User]()

[StockTick]()

[StockSubscription]()

## Build service strategy

## Testing the App

### Integration Testing

## Deliverables