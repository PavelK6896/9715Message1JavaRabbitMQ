package app.web.pavelk.message1.producer1;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class P2ExchangeSender {
    private static final String EXCHANGE = "EXCHANGE";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT);//создать эксченжер

            String message = "P2ExchangeSender info: Hello World!";

            //отправить месадж
            channel.basicPublish(EXCHANGE, "", null, message.getBytes("UTF-8"));
            System.out.println("P2ExchangeSender [x] Sent '" + message + "'");
        }
    }
}
