package app.web.pavelk.message1.consumer2.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfiguration3 {

    @Bean
    public Queue myQueue31() {
        return new Queue("query-example-3-1", false, false, false);
    }

    @Bean
    public Queue myQueue32() {
        return new Queue("query-example-3-2", false, false, false);
    }

    //один ех на две очереди
    @Bean
    public FanoutExchange fanoutExchangeA() {
        return new FanoutExchange("exchange-example-3");
    }

    @Bean
    public Binding binding31() {
        return BindingBuilder.bind(myQueue31()).to(fanoutExchangeA());
    }

    @Bean
    public Binding binding32() {
        return BindingBuilder.bind(myQueue32()).to(fanoutExchangeA());
    }

}
