package app.web.pavelk.message1.producer2.controller;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SampleController2 {
    Logger logger = Logger.getLogger(SampleController2.class);

    @Autowired
    AmqpTemplate template;

    @RequestMapping("/queue2")
    @ResponseBody
    String queue2() {
        logger.info("Emit to queue");
        for(int i = 0;i<10;i++)
            template.convertAndSend("query-example-2","Message " + i);
        return "Emit to queue";
    }
}
