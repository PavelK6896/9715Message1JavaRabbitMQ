package app.web.pavelk.message1.producer1;

import app.web.pavelk.message1.common1.MyMessage;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.SerializationUtils;

public class P4SerializationSender {
    private static final String Serialization = "Serialization";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(Serialization, BuiltinExchangeType.FANOUT, false, true, null);
            //commons сериалезует объект//возможно подключить джексон, гсон
            channel.basicPublish(Serialization, "", null,
                    SerializationUtils.serialize(new MyMessage("P4SerializationSender Hello!")));
            System.out.println("P4SerializationSender [x] Sent");
        }
    }
}
