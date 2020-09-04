package app.web.pavelk.message1.consumer2.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfiguration2 {

    @Bean
    public Queue myQueue21() {
        return new Queue("query-example-2");
    }

}
