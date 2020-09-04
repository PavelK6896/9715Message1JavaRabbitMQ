package app.web.pavelk.message1.consumer2.component;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@EnableRabbit
@Component
public class RabbitMqListener5 {
    Logger logger = Logger.getLogger(RabbitMqListener5.class);
    Random random = new Random();

    @RabbitListener(queues = "query-example-5-1")
    public void worker1(String message) {
        logger.info("accepted on worker 1 : " + message);
    }

    @RabbitListener(queues = "query-example-5-2")
    public void worker2(String message) {
        logger.info("accepted on worker 2 : " + message);
    }

}
