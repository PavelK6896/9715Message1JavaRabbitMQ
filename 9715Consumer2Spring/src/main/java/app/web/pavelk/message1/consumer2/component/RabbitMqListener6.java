package app.web.pavelk.message1.consumer2.component;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener6 {
    Logger logger = Logger.getLogger(RabbitMqListener6.class);

    @RabbitListener(queues = "query-example-6")
    public String worker1(String message) throws InterruptedException {
        logger.info("Received on worker : " + message);
        Thread.sleep(3000);
        return "Received on worker : " + message;
    }
}
