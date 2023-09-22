package com.upx.ticketsapi.util;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class QueueFactory implements InitializingBean {
    private final RabbitAdmin rabbitAdmin;

    public QueueFactory(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        rabbitAdmin.declareQueue(new Queue("user-keycloak-queue"));
        rabbitAdmin.declareQueue(new Queue("user-keycloak-queue-dlq"));
    }

}
