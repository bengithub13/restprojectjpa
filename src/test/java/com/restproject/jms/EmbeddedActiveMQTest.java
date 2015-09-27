package com.restproject.jms;




import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/mvc-dispatcher-servlet.xml" })
public class EmbeddedActiveMQTest extends AbstractTransactionalJUnit4SpringContextTests{
 @Autowired
    private JmsMessageSender messageProducer;
 

    @Before
    public void setUp() {

    }
 
    @Test
    public void testSendMessageToDefaultDestination() {
    	try{
        messageProducer
                .sendText("Send this message to default destination. Successful message!");
    	}
    	catch (Exception e){
    		
    	}
    	
    	}
    
}