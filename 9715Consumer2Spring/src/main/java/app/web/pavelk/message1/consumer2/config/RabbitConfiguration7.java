package app.web.pavelk.message1.consumer2.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration7 {

    @Bean
    public Queue myQueue70() {
        return new Queue("query-example-7");
    }
}
