package com.course.rabbitmqconsumer.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.course.rabbitmqconsumer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeJsonConsumer {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeJsonConsumer.class);
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@RabbitListener(queues = "course.employee")
	public void listen(String message) {
		
		Employee employee;
		try {
			employee = objectMapper.readValue(message, Employee.class);
			logger.info("Consuming Employee {}", employee);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
