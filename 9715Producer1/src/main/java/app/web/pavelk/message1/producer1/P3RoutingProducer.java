package app.web.pavelk.message1.producer1;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class P3RoutingProducer {
    private static final String EXCHANGE_TOPIC = "EXCHANGE_TOPIC";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            //--
            channel.exchangeDeclare(EXCHANGE_TOPIC, BuiltinExchangeType.TOPIC);
            String routingKey = ":com.i";
            String message = "message123";
            //--
            channel.basicPublish(EXCHANGE_TOPIC, routingKey, null, message.getBytes("UTF-8"));
            channel.basicPublish(EXCHANGE_TOPIC, "routingKey", null, message.getBytes("UTF-8"));
            System.out.println("P3RoutingProducer [x] Sent '" + routingKey + "':'" + message + "'");
        }
    }
}
