package app.web.pavelk.message1.consumer2.config;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration4 {

    @Bean
    public Queue myQueue41() {
        return new Queue("query-example-4-1");
    }

    @Bean
    public Queue myQueue42() {
        return new Queue("query-example-4-2");
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("exchange-example-4");
    }

    @Bean
    public Binding errorBinding1(){
        return BindingBuilder.bind(myQueue41()).to(directExchange()).with("error");
    }

    @Bean
    public Binding errorBinding2(){
        return BindingBuilder.bind(myQueue42()).to(directExchange()).with("error");
    }

    @Bean
    public Binding infoBinding(){
        return BindingBuilder.bind(myQueue42()).to(directExchange()).with("info");
    }

    @Bean
    public Binding warningBinding(){
        return BindingBuilder.bind(myQueue42()).to(directExchange()).with("warning");
    }

}
