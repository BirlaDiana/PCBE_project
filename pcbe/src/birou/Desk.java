package birou;
import java.util.concurrent.Semaphore;
import com.rabbitmq.client.*;

public class Desk {
	
	private final String queueName;
	private int deskNumber;
	private int numberOfEmp;
	private final Stamp stamp = new Stamp(deskNumber);
	private Semaphore sem ;
	public Desk(String queueName, int deskNumber, int numberOfEmp ) {

		this.queueName = queueName;
		this.deskNumber = deskNumber;
		this.numberOfEmp=numberOfEmp;
		sem=new Semaphore(this.numberOfEmp);
	}

	public void solveRequest(ConnectionFactory factory) throws Exception {
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(queueName, false, false, false, null);
		channel.queuePurge(queueName);
		channel.basicQos(1);

		System.out.println("[Birou " + deskNumber + "] " + "Asteptare clienti la " + queueName);

				DeliverCallback deliverCallback = (consumerTag, delivery) -> 
				{
	                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
	                        .Builder()
	                        .correlationId(delivery.getProperties().getCorrelationId())
	                        .build();	
	                		ThreadClass Thread=new ThreadClass(stamp,sem,channel,delivery,replyProps);
	                		Thread.start();
		};
		channel.basicConsume(queueName, true, deliverCallback, (consumerTag -> { }));
		}

	
}





	