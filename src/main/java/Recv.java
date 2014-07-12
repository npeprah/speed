import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by NanaKwesi on 7/10/14.
 */
public class Recv {

    private final static String QUEUE_NAME = "hello";

    public  static void main(String [] argv) throws IOException, InterruptedException {
        ConnectionFactory factory  = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("nana");
        factory.setPassword("kwesi123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);
        while(true)
        {
          QueueingConsumer.Delivery  delivery = consumer.nextDelivery();
          String message = new String(delivery.getBody());
          System.out.println("[x] Received '"+ message +"'");

        }
    }
}
