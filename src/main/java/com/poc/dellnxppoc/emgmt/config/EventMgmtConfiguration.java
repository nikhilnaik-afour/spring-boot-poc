package com.poc.dellnxppoc.emgmt.config;

import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.modelmapper.module.jsr310.Jsr310ModuleConfig;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


@Configuration
public class EventMgmtConfiguration {

    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Jsr310ModuleConfig config = Jsr310ModuleConfig.builder()
                .dateTimeFormatter(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                .dateFormatter(DateTimeFormatter.ISO_LOCAL_DATE).zoneId(ZoneOffset.UTC).build();

        // Configuration to avoid chaining of lazy loaded objects.
        modelMapper.getConfiguration().setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection));

        return modelMapper.registerModule(new Jsr310Module(config));
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

}