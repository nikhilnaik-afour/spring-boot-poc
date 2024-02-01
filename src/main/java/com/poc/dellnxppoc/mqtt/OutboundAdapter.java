package com.poc.dellnxppoc.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component
public class OutboundAdapter {

	private String clientId = MqttClient.generateClientId(); // need to set a unique clientId for each client

	@Value("${spring.mqtt.default-topic}")
	private String defaultTopic; // topic that will be used if no topic is specified during publishing of any message

	@Resource(name = "mqttClientFactory")
	private MqttPahoClientFactory mqttClientFactory;

	@Bean
	public MessageChannel mqttOutboundChannel() {
		return new DirectChannel();
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttOutboundChannel")
	public MessageHandler mqttOutbound() {
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId, mqttClientFactory);
		messageHandler.setAsync(true);
		messageHandler.setDefaultRetained(true); // last message is retained for a specific topic
		messageHandler.setDefaultTopic(defaultTopic);
		return messageHandler;
	}

	@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
	public interface MqttGateway {

		void sendToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

	}

}
