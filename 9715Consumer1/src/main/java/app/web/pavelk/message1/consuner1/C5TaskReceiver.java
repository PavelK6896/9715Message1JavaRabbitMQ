package app.web.pavelk.message1.consuner1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


public class C5TaskReceiver {
    private static final String TASK = "TASK";


    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        final Channel channel2 = connection.createChannel();

        Logger logger = LoggerFactory.getLogger(C5TaskReceiver.class);
        AtomicLong ll = new AtomicLong(0);
        AtomicInteger ii = new AtomicInteger(0);
        AtomicInteger ii2 = new AtomicInteger(0);
        long l = System.currentTimeMillis();

        //--
        channel.queueDeclare(TASK, true, false, false, null);
        channel2.queueDeclare(TASK, true, false, false, null);
        System.out.println("C5TaskReceiver [*] Waiting for messages");
        channel.basicQos(1);//берет только 1 сообщения
        channel2.basicQos(1);

        Thread t = new Thread(() -> {
            System.out.println("t.start();");
            DeliverCallback deliverCallback2 = (consumerTag, delivery) -> {
                System.out.println(ii2.incrementAndGet());
//                long l = System.currentTimeMillis();
                String message = new String(delivery.getBody(), "UTF-8");//берет задачю
                doWork(message);
//                logger.info("end 2");
                // System.out.println(ll.addAndGet (System.currentTimeMillis() - l));
                channel2.basicAck(delivery.getEnvelope().getDeliveryTag(), false);//следущая задача
            };
            try {
                channel2.basicConsume(TASK, false, deliverCallback2, consumerTag -> {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.setDaemon(true);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            System.out.println(ii.incrementAndGet());
            String message = new String(delivery.getBody(), "UTF-8");//берет задачю

            doWork(message);
//            if (true) {
//                throw new RuntimeException("Oops");
//            }
//            logger.info("end 1");
            System.out.println(System.currentTimeMillis() - l);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);//следущая задача
        };

        t.start();
        //autoAck - подтверждение обработки посылки // true - по умолчанию
        channel.basicConsume(TASK, false, deliverCallback, consumerTag -> {
        });
    }


    private static void doWork(String task) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
