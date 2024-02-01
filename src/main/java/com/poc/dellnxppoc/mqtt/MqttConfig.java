package com.poc.dellnxppoc.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

@Configuration
public class MqttConfig {

	private final String brokerUrl;
	private final String userName;
	private final String password;
	private final String defaultTopic;

	public MqttConfig(@Value("${spring.mqtt.broker-url}") String brokerUrl,
			@Value("${spring.mqtt.username}") String userName, @Value("${spring.mqtt.password}") String password,
			@Value("${spring.mqtt.default-topic}") String defaultTopic) {
		this.brokerUrl = brokerUrl;
		this.userName = userName;
		this.password = password;
		this.defaultTopic = defaultTopic;
	}

	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		MqttConnectOptions options = new MqttConnectOptions();
//        options.setServerURIs(new String[] { "tcp://host1:1883", "tcp://host2:1883" });	// For cluster
		options.setServerURIs(new String[] { brokerUrl });
		options.setUserName(userName);
		options.setPassword(password.toCharArray());
		options.setCleanSession(false);
		factory.setConnectionOptions(options);
		return factory;
	}

}
