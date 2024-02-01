package com.poc.dellnxppoc.mqtt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class MqttController {
	
	private final Logger LOG = LoggerFactory.getLogger(MqttController.class);
	
	@Autowired
	private MqttService mqttService;
	
	@PostMapping("/mqtt/publish")
	public void publishMessage(String message, String topic) {
		LOG.info("Received request to publish message message="+message+" ; topic="+topic);
		mqttService.publishMessage(message, topic);
	}
	
	@PostMapping("/mqtt/subscribe")
	public void subscribeToTopic(String topic) {
		LOG.info("Received request to subscribe to topic="+topic);
		mqttService.subscribeTopic(topic);
	}

}
