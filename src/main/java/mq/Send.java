package mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;



public class Send{
    private final static String QUEUE_NAME = "queueOne";

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        String message = "this is a message";

        try{
           Connection connection = connectionFactory.newConnection();
           Channel channel = connection.createChannel();
           channel.queueDeclare(QUEUE_NAME,false,false,false,null);
           channel.basicPublish("",QUEUE_NAME,null,message.getBytes("UTF-8"));
           System.out.println("Sent:"+ message);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
