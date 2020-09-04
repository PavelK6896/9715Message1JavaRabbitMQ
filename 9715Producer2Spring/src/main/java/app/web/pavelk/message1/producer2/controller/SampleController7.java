package app.web.pavelk.message1.producer2.controller;


import app.web.pavelk.message1.common1.MyMessage;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController7 {
    Logger logger = Logger.getLogger(SampleController6.class);

    @Autowired
    RabbitTemplate template;

    @RequestMapping("/queue7")
    @ResponseBody
    String error() {

        template.convertAndSend("query-example-7", new MyMessage("queue7"));
        logger.info("queue7");
        return "queue7";
    }


    //@EnableScheduling
    //@Scheduled(fixedDelay = 3000L)

}
