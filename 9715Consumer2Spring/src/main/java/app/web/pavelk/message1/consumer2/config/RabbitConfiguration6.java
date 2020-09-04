package app.web.pavelk.message1.consumer2.config;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration6 {

    @Bean
    public Queue myQueue60() {
        return new Queue("query-example-6");
    }
}
