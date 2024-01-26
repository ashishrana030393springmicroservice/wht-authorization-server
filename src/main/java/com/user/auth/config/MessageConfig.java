package com.user.auth.config;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
//import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.ErrorHandler;
//
//@Configuration
public class MessageConfig {
//    @Value("${spring.rabbitmq.virtual-host}")
//    private String virtualHost;
//    @Value("${spring.rabbitmq.username}")
//    private String username;
//    @Value("${spring.rabbitmq.password}")
//    private String password;
//    @Value("${spring.rabbitmq.host}")
//    private String host;
//    @Value("${spring.rabbitmq.port}")
//    private int port;
//    @Bean
//    Queue queue(){
//        return new Queue(Constant.SIGNUP_QUEUE);
//    }
//
//    @Bean
//    TopicExchange exchange(){
//        return new TopicExchange(Constant.SIGNUP_EXCHANGE);
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange, AmqpAdmin amqpAdmin){
//        amqpAdmin.declareQueue(queue);
//        return BindingBuilder.bind(queue).to(exchange).with(Constant.SIGNUP);
//    }
//
//    @Bean
//    MessageConverter converter(){
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setVirtualHost(virtualHost);
//        connectionFactory.setHost(host);
//        connectionFactory.setUsername(username);
//        connectionFactory.setPassword(password);
//        connectionFactory.setPort(port);
//        return connectionFactory;
//    }
//
//    @Bean
//    public AmqpTemplate template(){
//        final RabbitTemplate template = new RabbitTemplate(connectionFactory());
//        template.setMessageConverter(converter());
//        return template;
//    }
//
//    @Bean
//    public AmqpAdmin amqpAdmin(){
//        return new RabbitAdmin(connectionFactory());
//    }
//
//    @Bean
//    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(){
//        final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory());
//        factory.setMessageConverter(converter());
//        factory.setConcurrentConsumers(1);
//        factory.setMaxConcurrentConsumers(1);
//        factory.setErrorHandler(errorHandler());
//        return factory;
//    }
//    @Bean
//    public ErrorHandler errorHandler() {
//        return new ConditionalRejectingErrorHandler(new MyFatalExceptionStrategy());
//    }
//
//    public static class MyFatalExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {
//        private final Logger logger = LogManager.getLogger(getClass());
//        @Override
//        public boolean isFatal(Throwable t) {
//            if (t instanceof ListenerExecutionFailedException) {
//                ListenerExecutionFailedException lefe = (ListenerExecutionFailedException) t;
//                logger.error("Failed to process inbound message from queue "
//                        + lefe.getFailedMessage().getMessageProperties().getConsumerQueue()
//                        + "; failed message: " + lefe.getFailedMessage(), t);
//            }
//            return super.isFatal(t);
//        }
//    }
}
