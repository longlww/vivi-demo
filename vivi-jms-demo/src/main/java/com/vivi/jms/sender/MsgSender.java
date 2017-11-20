package com.vivi.jms.sender;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import com.vivi.jms.constant.Loggers;

public class MsgSender {
  private JmsTemplate jmsTemplate;
  private Destination destination;

  public MsgSender(JmsTemplate temp, Destination dest) {
    jmsTemplate = temp;
    destination = dest;
  }

  /**
   * 向指定队列发送消息
   */
  public void sendMessage(final String msg) {
    Loggers.SENDER.info("队列[" + destination.toString() + "]发送了消息: " + msg);
    jmsTemplate.send(destination, new MessageCreator() {
      public Message createMessage(Session session) throws JMSException {
        // TODO Auto-generated method stub
        return session.createTextMessage(msg);
      }
    });
  }

}
