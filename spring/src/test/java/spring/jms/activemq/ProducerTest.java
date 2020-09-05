package spring.jms.activemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:activemq-producer.xml")  //加载这个配置文件
public class ProducerTest {

    //点对点
    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsTemplate;

    //发布订阅
    @Autowired
    @Qualifier("jmsTopicTemplate")
    private JmsTemplate jmsTemplateTopic;

    /**
     * 点对点
     */
    @Test
    public void ptpSender() {
        /**
         * 参数一：指定队列名称
         * 参数二：MessageCreator接口
         */
        jmsTemplate.send("spring_queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage("spring_queue message");
                return message;
            }
        });
        System.out.println("spring_queue发送完毕");
    }

    /**
     * 发布订阅模式
     */
    @Test
    public void psSender() {
        /**
         * 参数一：指定队列名称
         * 参数二：MessageCreator接口
         */
        jmsTemplateTopic.send("spring_topic", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage("spring_topic message");
                return message;
            }
        });
        System.out.println("spring_topic发送完毕");
    }

}
