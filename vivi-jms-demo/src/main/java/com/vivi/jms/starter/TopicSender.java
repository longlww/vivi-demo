package com.vivi.jms.starter;

import javax.jms.Destination;

import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.vivi.jms.publisher.MsgPublisher;

public class TopicSender extends Thread {

  private MsgPublisher msgPublisher;
  private ApplicationContext context;

  private JmsTemplate jmsTemplate;
  private Destination destination;

  public TopicSender(ApplicationContext ctx) {
      context = ctx;
      initMsgSender(ctx);
    }

  public void initMsgSender(ApplicationContext ctx) {
    if (msgPublisher != null) {
      return;
    }
    if (context != null) {
      jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");
      destination = (Destination) ctx.getBean("topicDestination");
      if (jmsTemplate == null) {
        System.out.println("JmsTemplate is null!");
      }
      if (destination == null) {
        System.out.println("destination is null!");
      }
      msgPublisher = new MsgPublisher(jmsTemplate, destination);
    }
  }

  // 定义个消息发送的条数
  static int i = 0;

  public void run() {
    // 这里我们一秒发送一条消息的模式
    for (; i < 20; i++) {
      msgPublisher.sendMessage("send Test Topic Message: " + i);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

}


