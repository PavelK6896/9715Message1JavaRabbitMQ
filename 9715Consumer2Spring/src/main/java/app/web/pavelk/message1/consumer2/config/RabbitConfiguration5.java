package app.web.pavelk.message1.consumer2.config;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration5 {


    @Bean
    public Queue myQueue51() {
        return new Queue("query-example-5-1");
    }

    @Bean
    public Queue myQueue52() {
        return new Queue("query-example-5-2");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("exchange-example-5");
    }

    @Bean
    public Binding binding51() {
        return BindingBuilder.bind(myQueue51()).to(topicExchange()).with("*.orange.*");
    }

    @Bean
    public Binding binding52() {
        return BindingBuilder.bind(myQueue52()).to(topicExchange()).with("*.*.rabbit");
    }

    @Bean
    public Binding binding53() {
        return BindingBuilder.bind(myQueue52()).to(topicExchange()).with("lazy.#");
    }


}
