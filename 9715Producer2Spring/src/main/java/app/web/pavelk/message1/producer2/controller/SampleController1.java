package app.web.pavelk.message1.producer2.controller;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController1 {
    Logger logger = Logger.getLogger(SampleController1.class);

    @Autowired
    AmqpTemplate template;

    @RequestMapping("/queue")
    @ResponseBody
    String queue1() {
        logger.info("Emit to queue1");
        template.convertAndSend("queue1","Message to queue");
        return "Emit to queue";
    }
}
