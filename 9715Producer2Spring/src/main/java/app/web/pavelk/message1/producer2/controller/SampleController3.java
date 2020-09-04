package app.web.pavelk.message1.producer2.controller;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController3 {
    Logger logger = Logger.getLogger(SampleController3.class);

    @Autowired
    RabbitTemplate template;

    @RequestMapping("/emit")
    @ResponseBody
    String emit() {
        logger.info("Emit to exchange-example-3");
        template.setExchange("exchange-example-3");
        template.convertAndSend("Fanout message");
        return "Emit to exchange-example-3";
    }
}
