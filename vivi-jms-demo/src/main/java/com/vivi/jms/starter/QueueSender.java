package com.vivi.jms.starter;

import javax.jms.Destination;

import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import com.vivi.jms.sender.MsgSender;

public class QueueSender extends Thread {

  private MsgSender msgSender;
  private ApplicationContext context;

  private JmsTemplate jmsTemplate;
  private Destination destination;

  public QueueSender(ApplicationContext ctx) {
    context = ctx;
    initMsgSender(ctx);
  }

  public void initMsgSender(ApplicationContext ctx) {
    if (msgSender != null) {
      return;
    }
    if (context != null) {
      jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");
      destination = (Destination) ctx.getBean("queueDestination");
      if (jmsTemplate == null) {
        System.out.println("JmsTemplate is null!");
      }
      if (destination == null) {
        System.out.println("destination is null!");
      }
      msgSender = new MsgSender(jmsTemplate, destination);
    }
  }

  // 定义个消息发送的条数
  static int i = 0;

  public void run() {
    // 这里我们一秒发送一条消息的模式
    for (; i < 20; i++) {
      msgSender.sendMessage("send Test Queue Message: " + i);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

}
