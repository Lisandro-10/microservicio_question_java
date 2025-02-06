package com.lisandro.microservicioQuestions.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandro.microservicioQuestions.server.EnvironmentVars;
import com.lisandro.microservicioQuestions.utils.gson.GsonTools;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service
public class DirectPublisher {

    @Autowired
    EnvironmentVars environmentVars;

    public void publish(String exchange, String queue, RabbitEvent message) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(environmentVars.envData.rabbitServerUrl);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(exchange, "direct");
            channel.queueDeclare(queue, false, false, false, null);

            channel.queueBind(queue, exchange, queue);

            channel.basicPublish(exchange, queue, null, GsonTools.toJson(message).getBytes());

        } catch (Exception e) {
            System.out.println("RabbitMQ no se pudo encolar " + e);
        }
    }
}
