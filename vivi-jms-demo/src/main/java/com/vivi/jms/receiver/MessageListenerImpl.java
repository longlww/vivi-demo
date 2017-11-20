package com.vivi.jms.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Service;

import com.vivi.jms.constant.Loggers;

@Service("messageListenerImpl")
public class MessageListenerImpl implements MessageListener {

  public void onMessage(Message message) {
    if (message instanceof TextMessage) {
      TextMessage textMessage = (TextMessage) message;
      try {
        String text = textMessage.getText();
        Loggers.RECEIVER.info("队列接收到消息: " + text);
      } catch (JMSException e) {
        e.printStackTrace();
      }
    }
  }
}
