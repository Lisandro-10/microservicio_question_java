package com.lisandro.microservicioQuestions.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitHandler {

	private Connection connection;
	private Channel channel;
	
	public void setUpConnection() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			
			String getUserData = "user_data";
			channel.queueDeclare(getUserData, true, false, false, null);
			System.out.println("Cola declarada: " + getUserData);
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		if(channel != null)
			try {
				channel.close();
				if(connection != null) connection.close();
			} catch (IOException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
