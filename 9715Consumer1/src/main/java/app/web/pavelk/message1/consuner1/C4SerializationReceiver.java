package app.web.pavelk.message1.consuner1;


import app.web.pavelk.message1.common1.MyMessage;
import com.rabbitmq.client.*;
import org.apache.commons.lang3.SerializationUtils;


public class C4SerializationReceiver {
    private static final String Serialization = "Serialization";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        ///--
        channel.exchangeDeclare(Serialization, BuiltinExchangeType.FANOUT, false, true, null);
        String queueName = channel.queueDeclare().getQueue();
        System.out.println("My queue name: " + queueName);
        channel.queueBind(queueName, Serialization, "");
        System.out.println("C4SerializationReceiver [*] Waiting for messages");
        ///---
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            MyMessage mm = SerializationUtils.deserialize(delivery.getBody());//commons
            System.out.println("C4SerializationReceiver [x] Received '" + mm.getMsg() + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}
