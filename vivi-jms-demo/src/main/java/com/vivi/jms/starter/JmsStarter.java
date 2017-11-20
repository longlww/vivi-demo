package com.vivi.jms.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class JmsStarter {
  @Autowired
  private static QueueSender queueSender;
  private static TopicSender topicSender;

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("Hello dsdfad!");

    try {
      new JmsStarter().start();
    } catch (Exception ex) {

      ex.printStackTrace();
      System.exit(1);
    }
  }

  private void start() {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

    // JmsTemplate o = (JmsTemplate) context.getBean("jmsTemplate");
    // if (o == null) {
    // System.out.println("JmsTemplate is null!");
    // }
    queueSender = new QueueSender(context);
    queueSender.run();

    topicSender = new TopicSender(context);
    topicSender.run();
  }

}
