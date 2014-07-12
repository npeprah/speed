import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by NanaKwesi on 7/9/14.
 */
public class Send {

    private final static  String QUEUE_NAME= "hello";

    public static void  main(String [] argv) throws IOException
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("nana");
        factory.setPassword("kwesi123");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel =  connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message = "Hello World";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("[x] Sent '" + message + "'");
    }
}
