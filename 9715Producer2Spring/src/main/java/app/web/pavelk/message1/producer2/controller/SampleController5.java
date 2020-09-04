package app.web.pavelk.message1.producer2.controller;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController5 {
    Logger logger = Logger.getLogger(SampleController5.class);

    @Autowired
    RabbitTemplate template;

    @RequestMapping("/{key}/{message}")
    @ResponseBody
    String error(@PathVariable("key") String key, @PathVariable("message") String message) {
        logger.info(String.format("Emit '%s' to '%s'",message,key));
        template.setExchange("exchange-example-5");
        template.convertAndSend(key, message);
        return String.format("Emit '%s' to '%s'",message,key);
    }
}
