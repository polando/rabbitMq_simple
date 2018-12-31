package mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;




public class Receive {
    private final static String QUEUE_NAME = "queueOne";
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //the queue is also created in the receiver because consumer may start before publisher
        channel.queueDeclare("",false,false,false,null);
        System.out.println("Waiting for message...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Received: " + message);
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}
