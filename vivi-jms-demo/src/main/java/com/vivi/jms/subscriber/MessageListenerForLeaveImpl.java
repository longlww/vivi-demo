package com.vivi.jms.subscriber;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Service;

import com.vivi.jms.constant.Loggers;

@Service("messageListenerForLeaveImpl")
public class MessageListenerForLeaveImpl implements MessageListener {

  public void onMessage(Message message) {
    if (message instanceof TextMessage) {
      TextMessage textMessage = (TextMessage) message;
      try {
        String text = textMessage.getText();
        Loggers.RECEIVER.info("消费者接收消息: " + text);
      } catch (JMSException e) {
        e.printStackTrace();
      }
    }
  }
}