package com.poc.dellnxppoc;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import com.poc.dellnxppoc.db.dto.Client;
import com.poc.dellnxppoc.db.service.ClientService;

@SpringBootApplication
@EnableKafka
public class DellNxpPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(DellNxpPocApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(@Qualifier("jpaService") ClientService clientService) {
		return args -> {
			clientService.saveClient(new Client("Client_EXZ", "EZ", LocalDate.now()));
			System.out.println("Client saved!");
			List<Client> clients_result = clientService.getClients();
			System.out.println("Running commandrunner. Result\t" + clients_result.get(0).getName());
		};
	}

}
