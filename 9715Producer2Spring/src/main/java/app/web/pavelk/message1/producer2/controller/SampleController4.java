package app.web.pavelk.message1.producer2.controller;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController4 {
    Logger logger = Logger.getLogger(SampleController4.class);

    @Autowired
    RabbitTemplate template;

    @RequestMapping("/error1")
    @ResponseBody
    String error() {
        logger.info("Emit as error");
        template.setExchange("exchange-example-4");
        template.convertAndSend("error", "Error");
        return "Emit as error";
    }

    @RequestMapping("/info1")
    @ResponseBody
    String info() {
        logger.info("Emit as info");
        template.setExchange("exchange-example-4");
        template.convertAndSend("info", "Info");
        return "Emit as info";
    }

    @RequestMapping("/warning1")
    @ResponseBody
    String warning() {
        logger.info("Emit as warning");
        template.setExchange("exchange-example-4");
        template.convertAndSend("warning", "Warning");
        return "Emit as warning";
    }
}
