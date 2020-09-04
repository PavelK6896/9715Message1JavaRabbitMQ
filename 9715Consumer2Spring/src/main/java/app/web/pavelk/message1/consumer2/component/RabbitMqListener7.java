package app.web.pavelk.message1.consumer2.component;

import app.web.pavelk.message1.common1.MyMessage;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener7 {
    Logger logger = Logger.getLogger(RabbitMqListener7.class);

    @RabbitListener(queues = "query-example-7")
    public void worker1(MyMessage message) {
        logger.info("Received on worker : " + message);
        logger.info(message.ii);
    }
}
