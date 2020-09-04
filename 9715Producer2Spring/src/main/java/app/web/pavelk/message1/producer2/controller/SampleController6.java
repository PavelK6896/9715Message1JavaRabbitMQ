package app.web.pavelk.message1.producer2.controller;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController6 {
    Logger logger = Logger.getLogger(SampleController6.class);

    @Autowired
    RabbitTemplate template;

    @RequestMapping("/process/{message}")
    @ResponseBody
    String error(@PathVariable("message") String message) {
        logger.info(String.format("Emit '%s'",message));
        template.setReplyTimeout(10 * 1000);
        //ждет ответа
        String response = (String) template.convertSendAndReceive("query-example-6",message);
        logger.info(String.format("Received on producer '%s'",response));
        return String.valueOf("returned from worker : " + response);
    }
}
