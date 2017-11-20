package com.vivi.jms.publisher;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.vivi.jms.constant.Loggers;

public class MsgPublisher {
  private JmsTemplate jmsTemplate;
  private Destination destination;

  public MsgPublisher(JmsTemplate temp, Destination dest) {
    jmsTemplate = temp;
    destination = dest;
  }

  /**
   * 向指定主题发送消息
   */
  public void sendMessage(final String msg) {
    Loggers.SENDER.info("生产者生产消息[" + destination.toString() + "]: " + msg);
    jmsTemplate.send(destination, new MessageCreator() {
      public Message createMessage(Session session) throws JMSException {
        // TODO Auto-generated method stub
        return session.createTextMessage(msg);
      }
    });
  }
}
