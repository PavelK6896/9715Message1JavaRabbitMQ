package app.web.pavelk.message1.consuner1;

import com.rabbitmq.client.*;

import java.io.IOException;

public class C2ExchangeReceiver {//Exchange-Обмен
    private static final String EXCHANGE = "EXCHANGE";

    public static void main(String[] argv) throws Exception { //
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT);

        //но могут быть и другие очереди
        String queueName = channel.queueDeclare().getQueue();//создаеться случайная очередь буфер для ех
        String queueName2 = channel.queueDeclare().getQueue();//создаеться случайная очередь буфер для ех

        System.out.println("My queue name: " + queueName + " " + queueName2);
        channel.queueBind(queueName, EXCHANGE, "");// делаеться привязка
        channel.queueBind(queueName2, EXCHANGE, "");// делаеться привязка
        System.out.println("P2ExchangeReceiver [*] Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {//колбе прослушки
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("P2ExchangeReceiver [x] Received '" + message + "'");
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


        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });//слушатель
    }
}
