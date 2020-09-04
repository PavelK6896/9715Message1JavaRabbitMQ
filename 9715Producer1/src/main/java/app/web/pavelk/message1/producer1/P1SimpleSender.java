package app.web.pavelk.message1.producer1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class P1SimpleSender {
    private final static String HELLO = "Spring1";//имя очереди

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();//конект
        factory.setHost("localhost");//куда порт по умолчанию 5672
        factory.setPort(5672);

        try (Connection connection = factory.newConnection(); // открываем соиденение
             Channel channel = connection.createChannel()) {// создаем количество коналов по умолчанию

            // либо создать новую ли получить существующею   //настройки должны совпадать
            //exclusive эксклюзивный имя
            //durable долговечьность просто сохраняет на диск
            //autoDelete автоудаление после работы будет удалена
           // channel.queueDeclare(HELLO, false, false, false, null);
            channel.queueDeclare(HELLO, false, false, false, null);

            String message = "Hello World! hello P1SimpleSender";

            //отправитть сообщение ексченжер пропустили
            channel.basicPublish("", HELLO, null, message.getBytes());

            System.out.println("P1SimpleSender [x] Sent отправил '" + message + "'");
        }
    }
}
