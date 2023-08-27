**clients**
- id
- client_id
- secret
- authentication
**redirect_urls**
- id
- url
- client
**scopes**
- id
- scope
- client
**grant_types**
- id
- grant_type
- client
**Code verifier and challenge**
- code verifier is a 32 byte piece of data which is base64 encoded without padding.
- code challenge is created using sha256 hash function over code verifier. It is also base64 encoded without padding.
http://localhost:8080/oauth2/authorize?response_type=code&client_id=axkdw-skdsfs-ksdfks-akdfks&scope=openid&redirect_uri=http://127.0.0.1:4200/portfolio&code_challenge=dDAsHCRCvEx7DZ4g4y_iXMF65fIVdJR8cUFDTAFB6Gc&code_challenge_method=S256
verifier=random()
challenge=sha256(verifier)
**docker mysql**
- docker run -it  --rm -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=projectdb -v ${PWD}/db:/var/lib/mysql --net db-network --name db mysql:8

**Rabbitmq**
- Use @ EnableRabbit to enable support for Rabbit Listener.
- @Value injects properties from the resource file into the Component classes.
- Define a bean of Queue with a name and mark it as non-durable. Non-durable means that the queue and any messages on it will be removed when RabbitMQ is stopped. On the other hand, restarting the application won’t have any effect on the queue.
- For the exchange, we have four different types, but in our example, we have registered a bean of the DirectExchange type. It routes messages to a queue by matching a complete routing key.
  Then we created a bean for Binding to tie an exchange with the queue.
- To ensure that messages are delivered in JSON format, create a bean for MessageConverter
- Register ConnectionFactory bean to make a connection to RabbitMQ.
- Bean of rabbitTemplate serves the purpose of sending messages to the queue.
- To define RabbitAdmin, declare AmqpAdmin bean or define it in the ApplicationContext. It’s useful if we need queues to be automatically declared and bounded.
- For the container factory bean, we have used SimpleRabbitListenerContainerFactory. It is required since the infrastructure looks for a bean rabbitListenerContainerFactory as the factory’s source for creating message listener containers by default.
- The errorHandler is used to return some user-friendly Error Object when an exception is thrown by the listener.
  
**Routing keys for (DIRECT and TOPIC exchange)**
  - `*` (Star Wildcard):
    The * wildcard matches exactly one word in the routing key.
    For example, a routing pattern like app.*.logs would match routing keys like app.system.logs or app.security.logs, but not app.system.info.logs.
  
  - `#` (Hash Wildcard):
    The # wildcard matches zero or more words in the routing key.
    It can only be used at the end of the routing pattern.
    For example, a routing pattern like app.# would match routing keys like app.system.logs, app.security.logs, and even app.