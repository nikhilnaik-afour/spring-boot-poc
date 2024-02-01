package com.poc.dellnxppoc.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component
public class InboundAdapter {

	private final Logger LOG = LoggerFactory.getLogger(InboundAdapter.class);

	private String clientId = MqttClient.generateClientId();

	@Value("${spring.mqtt.broker-url}")
	private String brokerUrl;

	@Value("${spring.mqtt.username}")
	private String userName;

	@Value("${spring.mqtt.password}")
	private String password;

	@Value("${spring.mqtt.default-topic}")
	private String defaultTopic;

	@Resource(name = "mqttClientFactory")
	private MqttPahoClientFactory mqttClientFactory;

	@Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MqttPahoMessageDrivenChannelAdapter buildInboundAdapter() {
//		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("tcp://localhost:1883",
//				"testClient", "topic1", "topic2");
		LOG.info("########## Creating inbound adapter with topic=" + defaultTopic);
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(clientId,
				mqttClientFactory, defaultTopic);
		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(1); // QoS 0 = at most once; QoS 1 = at least once; QoS 2 = exactly once
		adapter.setOutputChannelName("mqttInputChannel");
		return adapter;
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler handler() {
		return new MessageHandler() {

			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
				LOG.info("######## : " + message.getHeaders().toString());
				LOG.info("######## Received payload: '" + message.getPayload() + "' for topic: " + topic);
			}

		};
	}

}
