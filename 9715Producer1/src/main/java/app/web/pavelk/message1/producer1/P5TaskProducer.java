package app.web.pavelk.message1.producer1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class P5TaskProducer {
    private static final String TASK = "TASK";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            //--
            channel.queueDeclare(TASK, true, false, false, null);
            String message = "New Task.....";
            //--
            for (int i = 0; i < 20; i++) {
                channel.basicPublish("", TASK, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
                System.out.println(i + " P5TaskProducer [x] Sent '" + message + "'");
            }
        }
    }
}
