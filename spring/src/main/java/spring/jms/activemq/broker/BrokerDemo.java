package spring.jms.activemq.broker;

/**
 * 用activemq来构建java应用---不依赖于ActiveMQ应用，只需要jar包即可实现
 *  　　这里主要是用Activemq Broker作为独立的消息服务器来构建Java应用。
 *  简单的说，就是在java应用中启动activemq。这种方式会以进程的方式启动一个新的JVM来支持连接。
 */
public class BrokerDemo {
//    public static void main(String[] args) throws Exception {
//        BrokerService brokerService = new BrokerService();
//        brokerService.setUseJmx(true);
//        brokerService.addConnector("tcp://localhost:61616");
//        brokerService.start();
//    }
}
