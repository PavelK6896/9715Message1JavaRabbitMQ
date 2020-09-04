package app.web.pavelk.message1.producer2.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    public static final String Spring1 = "Spring1";
    public static final String queue1 = "queue1";

    private RabbitTemplate rabbitTemplate;
    private AmqpTemplate amqpTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Autowired
    public void setTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @GetMapping("/q")
    public void q() {
        rabbitTemplate.convertAndSend(Spring1, "rabbitTemplate");
    }

    @GetMapping("/w")
    public void w() {
        amqpTemplate.convertAndSend(Spring1, "amqpTemplate");
    }

    @GetMapping("/e")
    public void e() {
        amqpTemplate.convertSendAndReceive(Spring1,"message");
    }

    @GetMapping("/r")
    public void r() {
        rabbitTemplate.convertAndSend(queue1, "queue1 rabbitTemplate");
    }
}
