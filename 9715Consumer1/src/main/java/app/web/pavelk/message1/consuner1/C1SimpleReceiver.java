package app.web.pavelk.message1.consuner1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class C1SimpleReceiver {
    private final static String HELLO = "Spring1";//имя очереди

    public static void main(String[] argv) throws Exception {//слушает очредь
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // либо создать новую ли получить существующею   //настройки должны совпадать
        //exclusive эксклюзивный имя
        //durable долговечьность просто сохраняет на диск
        //autoDelete автоудаление после работы будет удалена
        channel.queueDeclare(HELLO, false, false, false, null);

        System.out.println("SimpleReceiverApp1 [*] Waiting for messages слушает ");

        //калбек слушателя
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("SimpleReceiverApp1 [x] Received пришло:  '" + message + "'");
        };

        //вешаем слушатель
        channel.basicConsume(HELLO, true, deliverCallback, consumerTag -> {
        });
    }
}
