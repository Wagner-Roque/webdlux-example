package com.apirest.webflux.webflux;

import java.util.UUID;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.apirest.webflux.webflux.document.PlayList;
import com.apirest.webflux.webflux.repository.PlayListRepository;

import reactor.core.publisher.Flux;

@Component
public class DummyData implements CommandLineRunner {

	private final PlayListRepository playListRepository;
	
	public DummyData(PlayListRepository playListRepository) {
		this.playListRepository = playListRepository;
	}
// Popula os dados no banco mongoDB
	@Override
	public void run(String... args) throws Exception {
		playListRepository.deleteAll()
			.thenMany(Flux.just("API REST Spring Boot", "Deploy de uma aplicação java no IBM Cloud", "Java 8",
	              "Github", "Spring Security", "Web Service RESTFULL", "Bean no Spring Framework")
	              .map(nome -> new PlayList(UUID.randomUUID().toString(), nome))
	              .flatMap(playListRepository::save))
					.subscribe(System.out::println);
			
	}

}
