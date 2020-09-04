package app.web.pavelk.message1.consuner1;

import com.rabbitmq.client.*;

import java.io.IOException;

public class C3RoutingConsumer {//регекст ключь или шаблон
    private static final String EXCHANGE_TOPIC = "EXCHANGE_TOPIC";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //--
        channel.exchangeDeclare(EXCHANGE_TOPIC, BuiltinExchangeType.TOPIC);//топик
        String queueName = channel.queueDeclare().getQueue();//буфер
        String queueName2 = channel.queueDeclare().getQueue();//буфер
        String routingKey = ":com.*";
        String routingKey2 = "#";
        //* лубое слово
        //# любое количество слов
        channel.queueBind(queueName, EXCHANGE_TOPIC, routingKey);
        channel.queueBind(queueName2, EXCHANGE_TOPIC, routingKey2);
        System.out.println("C3RoutingConsumer [*] Waiting for messages with routing key (" + routingKey + "):");
        //--
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("C3RoutingConsumer [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");

        };

        Thread t = new Thread(() -> {
            System.out.println("t.start();");
            try {
                channel.basicConsume(queueName2, true, deliverCallback, consumerTag -> { });//слушатель
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.setDaemon(true);
        t.start();

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}
