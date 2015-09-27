package com.restproject.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.restproject.dao.DAOProxyFactory;

@Service("messageListener1")
public class JmsMessageConsumer implements MessageListener {

	@Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String msg = ((TextMessage) message).getText();
                System.out.println("Message has been consumed : " + msg);
                Logger logger = LoggerFactory.getLogger(JmsMessageConsumer.class);
        	    logger.info("Message has been consumed : " + msg);
        	
            } catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new IllegalArgumentException(
                    "Message must be of type TextMessage");
        }
    }
}
