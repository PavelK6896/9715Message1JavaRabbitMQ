package app.web.pavelk.message1.consumer2.component;


import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener1 {

    Logger logger = Logger.getLogger(RabbitMqListener1.class);

    @RabbitListener(queues = "queue1")
    public void processQueue1(Message message) {
        logger.info("Received from queue 1: " + message + " - " + new String(message.getBody()));
    }

}
