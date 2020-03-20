package com.elearning.common;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;


public class MailSender {

    private JmsTemplate mailTemplate;

    public void send(final MailMessage mailMessage) {
        mailTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(mailMessage);
            }
        });
    }

}
