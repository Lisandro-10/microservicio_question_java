package com.lisandro.microservicioQuestions.rabbit;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandro.microservicioQuestions.server.EnvironmentVars;
import com.lisandro.microservicioQuestions.server.ValidatorService;
import com.rabbitmq.client.*;

@Service
public class DirectConsumer {
	 	@Autowired
	    EnvironmentVars environmentVars;

	    @Autowired
	    ValidatorService validator;

	    private String exchange;
	    private String queue;
	    private final Map<String, EventProcessor> listeners = new HashMap<>();

	    public DirectConsumer init(String exchange, String queue) {
	        this.exchange = exchange;
	        this.queue = queue;
	        return this;
	    }

	    public DirectConsumer addProcessor(String event, EventProcessor listener) {
	        listeners.put(event, listener);
	        return this;
	    }

	    /**
	     * En caso de desconexión se conectara nuevamente en 10 segundos
	     */
	    public void startDelayed() {
	        new java.util.Timer().schedule(new java.util.TimerTask() {
	            @Override
	            public void run() {
	                start();
	            }
	        }, 10 * 1000); // En 10 segundos reintenta.
	    }

	    /**
	     * Conecta a rabbit para escuchar eventos
	     */
	    public void start() {
	    	System.out.println("Comienza intento de connection...");
	        try {
	            ConnectionFactory factory = new ConnectionFactory();
	            factory.setHost(environmentVars.envData.rabbitServerUrl);
	            Connection connection = factory.newConnection();
	            Channel channel = connection.createChannel();
	            
	            channel.exchangeDeclare(exchange, "direct");
	            channel.queueDeclare(queue, false, false, false, null);

	            channel.queueBind(queue, exchange, queue);

	            new Thread(() -> {
	                try {
	                    String consumerTag = channel.basicConsume(queue, true, new EventConsumer(channel));
	                    System.out.println("Consumer " + consumerTag + " escuchando...");
	                } catch (Exception e) {
	                	e.printStackTrace();
	                    startDelayed();
	                }
	            }).start();
	        } catch (Exception e) {
	        	e.printStackTrace();
	            startDelayed();
	        }
	    }

	    class EventConsumer extends DefaultConsumer {
	        EventConsumer(Channel channel) {
	            super(channel);
	        }

	        @Override
	        public void handleDelivery(String consumerTag, //
	                                   Envelope envelope, //
	                                   AMQP.BasicProperties properties, //
	                                   byte[] body) {
	            try {
	            	String message = new String(body, StandardCharsets.UTF_8);
	                System.out.println("📩 Mensaje recibido: " + message);
	                RabbitEvent event = RabbitEvent.fromJson(new String(body));
	                EventProcessor l = listeners.get("question_article_exist");
	                if (l != null) {

	                    l.process(event);
	                }
	                
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
	    }
}
