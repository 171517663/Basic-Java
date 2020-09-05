package jms.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PTP_Consumer {
    public static void main(String[] args) throws JMSException {
        //1、创建工厂连接对象，需要制定ip和端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //2、使用连接工厂创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3、开启连接
        connection.start();
        //4、使用连接对象创建会话（session）对象
        //参数一：是否开启事务
        //参数二：确认机制
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Queue queue = session.createQueue("test-queue");
        //6、使用会话对象创建xiaofeizhe对象
        MessageConsumer consumer = session.createConsumer(queue);

        //8、接收消息
        while (true) {
            Message message = consumer.receive();
            if (message == null) {
                break;
            }
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage)message;
                System.out.println(msg.getText());
            }
        }

        //9、关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
