package com.vinod.test;

import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageProducerExample {
	public static void main(String[] args) {
		try {

			// Create a ConnectionFactory

			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin",
					ActiveMQConnection.DEFAULT_BROKER_URL);

			// Create a Connection

			Connection connection = connectionFactory.createConnection();

			connection.start();

			// Create a Session

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination

			Destination destination = session.createQueue("testMQ");

			// Create a MessageProducer from the Session to the Queue

			MessageProducer producer = session.createProducer(destination);

			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			// Create a messages

			TextMessage message = session.createTextMessage("Helloworld");

			//sending message
			producer.send(message);
			session.close();
			connection.close();

			System.out.println("Message sent");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
