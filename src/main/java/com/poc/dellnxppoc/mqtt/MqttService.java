package com.poc.dellnxppoc.mqtt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Service;

import com.poc.dellnxppoc.mqtt.OutboundAdapter.MqttGateway;

@Service
public class MqttService {

	private final Logger LOG = LoggerFactory.getLogger(MqttService.class);

	@Autowired
	private MqttGateway mqttGateway;

	@Autowired
	private MqttPahoMessageDrivenChannelAdapter mqttAdapter;

	public void publishMessage(String message, String topic) {
		mqttGateway.sendToMqtt(message, topic);
		LOG.info("Published successfuly to topic=" + topic);
	}

	public void subscribeTopic(String topic) {
		mqttAdapter.addTopic(topic);
		LOG.info("Subscribed successfuly to topic=" + topic);
	}

}
